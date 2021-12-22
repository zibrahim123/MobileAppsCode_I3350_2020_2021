package com.example.myfirsttestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
       Intent i = getIntent();
       TextView fn = findViewById(R.id.txtFirstName);
        TextView ln = findViewById(R.id.txtLastName);
        TextView pn = findViewById(R.id.txtPhoneNumber);
        TextView email = findViewById(R.id.txtEmail);
        fn.setText(i.getStringExtra("fname"));
        ln.setText(i.getStringExtra("lname"));
        pn.setText(i.getStringExtra("phone"));
        email.setText(i.getStringExtra("email"));
    }
}