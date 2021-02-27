package com.example.broadcast_receiver_phonecalls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private PhoneStateReceiver rec;
    private EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (EditText) findViewById(R.id.phonestxt);
        rec = new PhoneStateReceiver(txt);
        registerReceiver(rec, new IntentFilter("android.intent.action.PHONE_STATE"));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(rec);
    }
}