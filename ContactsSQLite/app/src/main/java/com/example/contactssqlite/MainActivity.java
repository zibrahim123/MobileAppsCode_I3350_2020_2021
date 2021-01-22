package com.example.contactssqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView mylist;
    List<Contact> lstContacts;
    dbHelper db;
    ArrayAdapter<String> adapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mytoolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        db = new dbHelper(this);
        mylist = findViewById(R.id.lstContacts);
        lstContacts = db.getAllContacts();
        adapt = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,lstContacts);
        mylist.setAdapter(adapt);

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ViewContact.class);
                Contact c = lstContacts.get(position);
                intent.putExtra("id",""+c.getID());
                intent.putExtra("name",c.getName());
                intent.putExtra("tel",c.getPhoneNumber());
                startActivity(intent);
            }
        });

        mylist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact c = lstContacts.get(position);
                int l = db.deleteContact(c);
                if(l==1){
                    lstContacts.remove(position);
                    adapt.notifyDataSetChanged();
                }
                else
                    Toast.makeText(getApplicationContext(),"Delete contact failed",Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(this,AddContact.class);
                //startActivity(intent);
                startActivityForResult(intent, 123);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 123) {
            if(resultCode == RESULT_OK) {
                // came back from SecondActivity
                int id = intent.getIntExtra("id", -1);
                if(id != -1) {
                    String name = intent.getStringExtra("name");
                    String tel = intent.getStringExtra("tel");
                    lstContacts.add(new Contact(id, name, tel));
                    adapt.notifyDataSetChanged();
                }
                else
                    Toast.makeText(this,"add contact failed",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"add contact canceled",Toast.LENGTH_LONG).show();
            }
        }
    }

}