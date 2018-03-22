package com.mittas.taskmanager.ui.add;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.regex.Pattern;


public class AddTaskActivity extends AppCompatActivity {
    private AddTaskViewModel addTaskViewModel;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private String filePath;
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
                if (isEditTextEmpty(nameEditText) || isStringEmpty(filePath)) {
                    Toast.makeText(AddTaskActivity.this, "You need to write a name and choose a file",
                            Toast.LENGTH_SHORT).show();
                } else {
                    addTaskViewModel.addTask(new Task(
                            nameEditText.getText().toString(),
                            descriptionEditText.getText().toString(),
                            filePath,
                            Task.pending
                    ));
                    finish();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
            startFilePicker();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startFilePicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(1)
                .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
                .withFilterDirectories(true) // Set directories filterable (false by default)
                .withHiddenFiles(true) // Show hidden files and folders
                .withRootPath()
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            Toast.makeText(this, "File path ["+filePath+"]", Toast.LENGTH_LONG).show();

        }
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


}
