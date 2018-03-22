package com.mittas.taskmanager.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mittas.taskmanager.R;
import com.mittas.taskmanager.data.AppDatabase;

public class AddTaskActivity extends AppCompatActivity {
    private Context context = this;
    private AppDatabase db = AppDatabase.getAppDatabase(this);
    EditText firstName;
    EditText lastName;
    EditText email;
    Button button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_user);


        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(firstName) || isEmpty(lastName) || isEmpty(email)) {
                    Toast.makeText(context, "Da fuck you doing maaaaan", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(firstName.getText().toString(), lastName.getText().toString(),
                            email.getText().toString());

                    db.userDao().insertUsers(user);
                }
            }
        });
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
