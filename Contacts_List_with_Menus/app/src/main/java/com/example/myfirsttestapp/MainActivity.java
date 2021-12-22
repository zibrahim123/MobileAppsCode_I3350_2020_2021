package com.example.myfirsttestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> listContacts = new ArrayList<Contact>();
    private ArrayAdapter ar;
    private ListView lstv;
    public static final int code = 123;
    private ActionMode mActionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar t = (Toolbar) findViewById(R.id.mytoolbar);
        //setSupportActionBar(t);

        lstv = (ListView) findViewById(R.id.lstContacts);
        //get the list of contacts from the DB and then insert them in the list view
        ar = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listContacts);
        lstv.setAdapter(ar);

        lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),ViewActivity.class);
                Contact c = listContacts.get(position);
                i.putExtra("fname", c.getFirstName());
                i.putExtra("lname", c.getLastName());
                i.putExtra("phone", c.getPhoneNumber());
                i.putExtra("email", c.getEmail());
                startActivity(i);
            }
        });

        registerForContextMenu(lstv);

        // for temporary contextual menu
        TextView txt = findViewById(R.id.textView);
        txt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) return false;
                mActionMode =
                        MainActivity.this.startActionMode(new android.view.ActionMode.Callback() {
                            @Override
                            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                                MenuInflater inflater = mode.getMenuInflater();
                                inflater.inflate(R.menu.contextual_menu_2, menu);
                                return true;

                            }

                            @Override
                            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                                return false;
                            }

                            @Override
                            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.citem_edit_2:
                                        // Perform action for the edit menu item.
                                        txt.setText("Edit mode is clicked");
                                        mode.finish(); // Action picked, so close the action bar.
                                        return true;
                                    case R.id.citem_share_2:
                                        // Perform action for the share menu item.
                                        txt.setText("share mode is clicked");
                                        mode.finish(); // Action picked, so close the action bar.
                                        return true;
                                    case R.id.citem_delete_2:
                                        // Perform action for the delete menu item.
                                        txt.setText("Delete mode is clicked");
                                        mode.finish(); // Action picked, so close the action bar.
                                        return true;
                                    default:
                                        return false;
                                }
                            }

                            @Override
                            public void onDestroyActionMode(android.view.ActionMode mode) {
                                mActionMode = null;
                            }
                        });
                txt.setSelected(true);
                return true;
            }
        });
        //for popu menu

        ImageButton mButton = findViewById(R.id.button_popup);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, mButton);
                popup.getMenuInflater().inflate(
                        R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.popup_forward:
                                // Implement code for Forward button.
                                return true;
                            case R.id.popup_reply:
                                // Implement code for reply button.
                                return true;
                            case R.id.popup_replyall:
                                // Implement code for reply all button.
                                return true;
                            default:
                                return false;
                        }

                    }
                    // implement click listener.
                });
                popup.show();
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_option) {
            goToAdd();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    public void goToAdd(){
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, code);
    }

    protected void onActivityResult(int reqCode, int resCode, Intent intent) {

        super.onActivityResult(reqCode, resCode, intent);
        if(reqCode == code){
            if(resCode == RESULT_OK){
                String fn = intent.getStringExtra("FN");
                String ln = intent.getStringExtra("LN");
                String pn = intent.getStringExtra("PN");
                String ea = intent.getStringExtra("EA");
                Contact c = new Contact(fn, ln, pn, ea);
                listContacts.add(c);
                ar.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.citem_view:
                return true;
            case R.id.citem_edit:
                return true;
            case R.id.citem_delete:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }



}