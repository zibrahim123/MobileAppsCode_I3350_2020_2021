package com.example.login_signup_withfiles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity {
    EditText usernameedt, passwordedt;
    CheckBox chk;
    SharedPreferences mPreferences;
    SharedPreferences.Editor myeditor;
    private final String namefile = "com.example.login_signup_withfiles";
    private final String fileUsers = "users.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        usernameedt = findViewById(R.id.edtusername);
        passwordedt = findViewById(R.id.edtpassword);
        chk = findViewById(R.id.chkRememberMe);
        mPreferences =getSharedPreferences(namefile,MODE_PRIVATE);
        myeditor = mPreferences.edit();
        String storedusername = mPreferences.getString("username", "");
        String storedpassword = mPreferences.getString("password","");
        if(!storedusername.equals("") && !storedpassword.equals("")) {
            usernameedt.setText(storedusername);
            passwordedt.setText(storedpassword);
        }


    }

    public void login(View v){
        File file = new File(getFilesDir(), fileUsers);
        String username = usernameedt.getText().toString();
        String password = passwordedt.getText().toString();
        Scanner scan = null;
        int found=0;
        if(username.isEmpty() || password.isEmpty()){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Username and password cannot be empty");
            alert.setTitle("Empty field(s)");
            alert.setPositiveButton("OK",null);
            alert.setCancelable(false);
            alert.create().show();
            return;
        }
        System.out.println(file.getName());
        if(file.exists()){
            //Toast.makeText(this,"file exists", Toast.LENGTH_LONG).show();
            try {
                scan = new Scanner(openFileInput(fileUsers));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] data = line.split("###");
                if(data[0].equals(username) && data[1].equals(password)){
                    //Toast.makeText(this,"found", Toast.LENGTH_LONG).show();
                    found = 1;
                    if(chk.isChecked())
                        saveData(username,password);
                    Intent intent = new Intent(getApplicationContext(),LoggedInActivity.class);
                    startActivity(intent);
                }

            }
        }
        if(found == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Incorrect username or password. If you do not have an account, please register.");
            alert.setTitle("Login failed");
            alert.setPositiveButton("OK", null);
            alert.setCancelable(false);
            alert.create().show();
        }
    }

    public void saveData(String user, String pass){
        myeditor.putString("username",user);
        myeditor.putString("password",pass);
        myeditor.apply();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    switch (item.getItemId()){
        case R.id.registeroption:
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivityForResult(intent, 123);
            return true;
        case R.id.clear:
            myeditor.clear();
            myeditor.apply();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 123) {
// came back from SecondActivity
            String user = intent.getStringExtra("username");
            String pass = intent.getStringExtra("password");
            usernameedt.setText(user);
            passwordedt.setText(pass);
        }
    }

}