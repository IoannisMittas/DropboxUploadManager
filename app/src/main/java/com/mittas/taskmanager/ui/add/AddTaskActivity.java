package com.mittas.taskmanager.ui.add;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mittas.taskmanager.R;
import com.mittas.taskmanager.data.Task;
import com.mittas.taskmanager.viewmodel.AddTaskViewModel;


public class AddTaskActivity extends AppCompatActivity {
    private AddTaskViewModel addTaskViewModel;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private String filePath = "mouni";    // TODO temp -------------------------------------------------------
    private Button saveButton;
    private Button cancelButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_task);

        Toolbar toolbar = findViewById(R.id.toolbar_addTask);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addTaskViewModel = ViewModelProviders.of(this).get(AddTaskViewModel.class);

        nameEditText = findViewById(R.id.textInput_name);
        descriptionEditText = findViewById(R.id.textInput_description);
        // TODO filepath from below
        saveButton = findViewById(R.id.button_save);
        cancelButton = findViewById(R.id.button_cancel);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // name and filepath can't be empty
                if(isEditTextEmpty(nameEditText) || isStringEmpty(filePath)){
                    Toast.makeText(AddTaskActivity.this, "You need to write a name and choose a file",
                            Toast.LENGTH_SHORT).show();
                } else {
                    addTaskViewModel.addTask(new Task(
                            nameEditText.getText().toString(),
                            descriptionEditText.getText().toString(),
                            filePath
                    ));
                    // TODO put in pending task
                     finish();
                }
            }
        });
    }

    private boolean isEditTextEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private boolean isStringEmpty(String text) {
        if (text.trim().length() > 0)
            return false;

        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_file) {
            // TODO start filepicker
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
