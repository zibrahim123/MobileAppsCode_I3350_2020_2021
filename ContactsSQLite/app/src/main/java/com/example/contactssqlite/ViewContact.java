package com.example.contactssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewContact extends AppCompatActivity {
    TextView txtviewid, txtviewname,txtviewphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        txtviewid = findViewById(R.id.txtId);
        txtviewname = findViewById(R.id.txtName);
        txtviewphone = findViewById(R.id.txtPhone);
        Intent intent = getIntent();
        txtviewid.setText(intent.getStringExtra("id"));
        txtviewname.setText(intent.getStringExtra("name"));
        txtviewphone.setText(intent.getStringExtra("tel"));

    }
}