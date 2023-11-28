package com.hfad.svo_education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinishWindowActivity extends AppCompatActivity {

    private Button finishTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_window);

        finishTestButton = findViewById(R.id.answerButton);
        finishTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinishWindowActivity.this, ListTestsActivity.class);
                startActivity(intent);
            }
        });

    }
}