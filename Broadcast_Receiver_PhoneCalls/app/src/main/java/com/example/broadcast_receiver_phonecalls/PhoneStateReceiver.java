package com.example.broadcast_receiver_phonecalls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class PhoneStateReceiver extends BroadcastReceiver {
    private Context c;
    private EditText edt;

    private String savedNumber="";
    private Date startTime;
    private Date endTime;
    private long duration=-1;
    private boolean ringing=false, hangedon=false, hangedoff=false;
    private String number="";

    public PhoneStateReceiver(EditText e){
        edt = e;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        c = context;
        final TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                TelephonyManager telephony = (TelephonyManager) context.getSystemService(c.TELEPHONY_SERVICE);
                if (state == telephony.CALL_STATE_RINGING) {
                    ringing = true;
                    number = incomingNumber;
                } else if(state == telephony.CALL_STATE_OFFHOOK){
                    ringing = false;
                    hangedon = true;
                    startTime = new Date();
                } else if(state == telephony.CALL_STATE_IDLE) {
                    if (hangedon == true) {
                        hangedon = false;
                        endTime = new Date();
                        duration = endTime.getTime() - startTime.getTime();
                        edt.append(number + " : duration = " + (duration / 1000) + " seconds\n");
                        Toast.makeText(c,number + " (" + (duration / 1000) + " seconds)\n",Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);

    }
}
