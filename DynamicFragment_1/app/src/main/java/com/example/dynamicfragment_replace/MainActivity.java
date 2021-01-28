package com.example.dynamicfragment_replace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get FragmentTransaction associated with this Activity
        FragmentManager manager = getSupportFragmentManager( );
        FragmentTransaction transaction = manager.beginTransaction( );
        //Create instance of your Fragment
        MyListFragment fragment = new MyListFragment();
        //Add Fragment instance to your Activity
        transaction.add(R.id.frmLayout, fragment,"fraglist");
        transaction.commit();

    }
}