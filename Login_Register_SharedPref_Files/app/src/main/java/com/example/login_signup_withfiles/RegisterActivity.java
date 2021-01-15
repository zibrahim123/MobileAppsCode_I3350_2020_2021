package com.example.login_signup_withfiles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtuser, edtpass1, edtpass2;
    private final String fileUsers = "users.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtuser = findViewById(R.id.edtUsernameRegister);
        edtpass1 = findViewById(R.id.edtPasswordRegister);
        edtpass2 = findViewById(R.id.edtPasswordRetypedRegister);
    }
    public void registerUser(View v) {
        String user = edtuser.getText().toString();
        String pass1 = edtpass1.getText().toString();
        String pass2 = edtpass2.getText().toString();
        if (user.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("All fields are required");
            alert.setTitle("Empty field(s)");
            alert.setPositiveButton("OK", null);
            alert.setCancelable(false);
            alert.create().show();
            return;
        }

        if (!pass1.equals(pass2)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Passwords should match");
            alert.setTitle("Password error");
            alert.setPositiveButton("OK", null);
            alert.setCancelable(false);
            alert.create().show();
            return;
        }

        if (checkUsernameExist(user) == false) {
            try {
                PrintStream ps = new PrintStream(openFileOutput(fileUsers, MODE_APPEND));
                ps.println(user);
                ps.println(pass1);
                ps.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent();
            intent.putExtra("username", user);
            intent.putExtra("password", pass1);
            setResult(RESULT_OK, intent);
            finish();
        }
        else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Username already exists. Please choose another one");
            alert.setTitle("Username Already Taken");
            alert.setPositiveButton("OK",null);
            alert.setCancelable(false);
            alert.create().show();
        }
    }
    public boolean checkUsernameExist(String username){
        File file = new File(getFilesDir(), fileUsers);
        Scanner scan = null;
        if(file.exists()){
            try {
                scan = new Scanner(openFileInput(fileUsers));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while(scan.hasNextLine()){
                String line1 = scan.nextLine();
                String line2 = scan.nextLine();
                if(line1.equals(username))
                    return true;
            }
        }
        return false;
    }
}