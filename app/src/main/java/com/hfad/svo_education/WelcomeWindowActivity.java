package com.hfad.svo_education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeWindowActivity extends AppCompatActivity {

    private Button startTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_window);

        startTestButton = findViewById(R.id.answerButton);
        startTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeWindowActivity.this, TestsActivity.class);
                startActivity(intent);
            }
        });
    }
}