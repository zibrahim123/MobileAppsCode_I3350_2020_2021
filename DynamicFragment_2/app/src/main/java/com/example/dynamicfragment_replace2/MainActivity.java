package com.example.dynamicfragment_replace2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.View;

import com.example.dynamicfragment_replace.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void setFrag(View v){
        //get FragmentTransaction associated with this Activity
        FragmentManager manager = getSupportFragmentManager( );
        FragmentTransaction transaction = manager.beginTransaction( );
        //Create instance of your Fragment
        Fragment frag;
        if(v.getId() == R.id.btn1)
            frag = new Fragment1();
        else
            frag = new Fragment2();

        //Add Fragment instance to your Activity
        transaction.replace(R.id.frmLayout, frag);
        transaction.commit();
    }
}