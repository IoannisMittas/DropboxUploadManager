package com.mittas.taskmanager.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mittas.taskmanager.R;


public class AddTaskActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText email;
    Button button;


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


//        firstName = findViewById(R.id.first_name);
//        lastName = findViewById(R.id.last_name);
//        email = findViewById(R.id.email);
//        button = findViewById(R.id.button);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isEmpty(firstName) || isEmpty(lastName) || isEmpty(email)) {
//                    Toast.makeText(context, "Da fuck you doing maaaaan", Toast.LENGTH_SHORT).show();
//                } else {
//                    User user = new User(firstName.getText().toString(), lastName.getText().toString(),
//                            email.getText().toString());
//
//                    db.userDao().insertUsers(user);
//                }
//            }
//        });
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
