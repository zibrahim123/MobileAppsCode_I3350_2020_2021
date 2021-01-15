package com.example.swipetorefreshapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> data = new ArrayList<String>();
    ArrayAdapter<String> adapt;
    ListView lst;
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lst = findViewById(R.id.lstview);
        data.add(i+"");
        adapt = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,data);
        lst.setAdapter(adapt);
        adapt.notifyDataSetChanged();
        i++;

        SwipeRefreshLayout sp = findViewById(R.id.sprefresh);
        sp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.add(i+"");
                i++;
                adapt.notifyDataSetChanged();
                sp.setRefreshing(false);
            }
        });
    }
}