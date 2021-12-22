package com.example.myfirsttestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private EditText edtFN, edtLN, edtPN, edtEA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edtFN = (EditText) findViewById(R.id.edtFirstName);
        edtLN = (EditText) findViewById(R.id.edtLastName);
        edtPN = (EditText) findViewById(R.id.edtPhoneNumber);
        edtEA = (EditText) findViewById(R.id.edtEmail);
    }

    public void add(View v){
        String fn = edtFN.getText().toString();
        String ln = edtLN.getText().toString();
        String pn = edtPN.getText().toString();
        String ea = edtEA.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("FN", fn);
        intent.putExtra("LN", ln);
        intent.putExtra("PN",pn);
        intent.putExtra("EA", ea);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View v){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}