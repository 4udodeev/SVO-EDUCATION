package com.hfad.svo_education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ListTestsActivity extends AppCompatActivity {

    private ImageButton menuButton, lulkaButton, sosudiButton, electroButton, vysotaButton, podemButton, handlingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tests);

       ImageButton menuButton = findViewById(R.id.menuButton2);
        ImageButton lulkaButton = findViewById(R.id.lulkaButton);
        ImageButton sosudiButton = findViewById(R.id.sosudiButton);
        ImageButton electroButton = findViewById(R.id.electroButton);
        ImageButton vysotaButton = findViewById(R.id.vysotaButton);
        ImageButton podemButton = findViewById(R.id.podemButton);
        ImageButton nazemkaButton = findViewById(R.id.nazemkaButton);

        electroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListTestsActivity.this, WelcomeWindowActivity.class);
                startActivity(intent);
            }
        });



    }
}