package com.example.broadcast_receveir_customcase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {
        private TextView t;
        private MyReceiver mr;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            t=(TextView) findViewById(R.id.txt);
            mr = new MyReceiver();
            registerReceiver(mr, new IntentFilter("com.example.zein.broadcastreceiver_2"));
        }
        public void brodcastSignal(View v){
            Intent intent = new Intent("com.example.zein.broadcastreceiver_2");
            t.setText("Broadcasting ...");
            intent.putExtra("message","The match has started.");
            t.setText("Sent");
            sendBroadcast(intent);

        }
        public class MyReceiver extends BroadcastReceiver {

            @Override
            public void onReceive(Context context, Intent intent) {
                String message = intent.getStringExtra("message");
                try{
                    Thread.sleep((5000));
                }
                catch(Exception ex){
                    Toast.makeText(getApplicationContext(),"Cannot pause the thread",Toast.LENGTH_LONG).show();
                }
                t.setText(message+"");
                //Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        }
        protected void onDestroy() {
            super.onDestroy();

            unregisterReceiver(mr);
        }
    }
