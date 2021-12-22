package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResltActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reslt);
        Intent i = getIntent();
        String v = i.getStringExtra("correct");
        TextView txt = findViewById(R.id.textView);
        txt.setText("You answered "+v+" correct answers out of 10");
    }
}