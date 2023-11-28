package com.hfad.svo_education;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText loginField, passwordField;
    private Button loginButton, signupButton;
    private TextView signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.signupButton);
        TextView signupBtn = findViewById(R.id.signupBtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText loginField = findViewById(R.id.signupLastname);
                EditText passwordField = findViewById(R.id.signupPassword);

                String loginText = loginField.getText().toString();
                String passwordText = passwordField.getText().toString();

                logon(loginText, passwordText);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignup = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intentSignup);
            }
        });

    }

    private void logon(String loginText, String passwordText){

        // добавить поиск сооответствия логина-пароля по базе PostgreSQL

        if (loginText.equals("1") && passwordText.equals("1")) {
            Intent intent = new Intent(this, ListTestsActivity.class);
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("ошибка")
                    .setCancelable(false)
                    .setMessage("Не корректный логин или пароль")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

}