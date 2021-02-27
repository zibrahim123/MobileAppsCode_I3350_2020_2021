package com.example.broadcast_receiver_airplanemode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static TextView txtv;
    MyReceiver mr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtv = (TextView) findViewById(R.id.txtview);
        mr = new MyReceiver();
        registerReceiver(mr, new IntentFilter("android.intent.action.AIRPLANE_MODE"));
    }

    public static class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // assumes WordService is a registered service
            //intent = new Intent(context, WordService.class);
            //context.startService(intent);
            Toast.makeText(context,"The state of airplane mode has changed",Toast.LENGTH_LONG).show();
            String s = isAirplaneModeOn(context)==true?"ON":"OFF";
            txtv.setText("The aeroplane mode is passed to "+ s);
        }
        private boolean isAirplaneModeOn(Context context) {

            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mr);

    }
}