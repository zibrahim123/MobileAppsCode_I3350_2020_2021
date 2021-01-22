package com.example.contactssqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {
    dbHelper db;
    EditText edtname, edtphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        db = new dbHelper(this);
        edtname = findViewById(R.id.edtName);
        edtphone = findViewById(R.id.edtPhone);
    }

    public void addContact(View v){
        String name = edtname.getText().toString();
        String phone = edtphone.getText().toString();
        if (name.isEmpty() || phone.isEmpty()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("All fields are required");
            alert.setTitle("Empty field(s)");
            alert.setPositiveButton("OK", null);
            alert.setCancelable(false);
            alert.create().show();
            return;
        }
        long id = db.addContact(new Contact(name, phone));
        //Toast.makeText(this,""+id,Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        intent.putExtra("id", (int) id);
        intent.putExtra("name", name);
        intent.putExtra("tel", phone);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View v){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}