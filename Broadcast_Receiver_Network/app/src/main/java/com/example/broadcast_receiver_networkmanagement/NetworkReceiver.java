package com.example.broadcast_receiver_networkmanagement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class NetworkReceiver extends BroadcastReceiver {
    private TextView txt;
    private Context ctx;

    public NetworkReceiver(){}
    public NetworkReceiver(TextView t, Context c){
        super();
        txt=t;
        ctx = c;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        //if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            txt.setText(isNetworkAvailable());
        //}
    }

    public String isNetworkAvailable() {
        //ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        ConnectivityManager cm;
        NetworkInfo info = null;
        String result="";
        try {
            cm = (ConnectivityManager)
                    ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        catch (Exception e) {
            return e.getMessage();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //if api version is >=23 (Marshmallow)
            if (cm!=null) {
                NetworkCapabilities networkCapabilities=cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (networkCapabilities!=null) {
                    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                        result = "WIFI";
                    else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                        result = "CELLULAR";
                    else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                        result = "Ethernet";
                    else
                        result = "OTHER";
                }
            }
        }

        else {
            if (cm != null){

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        result = "WIFI";
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        result = "CELLULAR";
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_ETHERNET) {
                        result = "VPN";
                    }
                    else
                        result="OTHER";
                }
            }
        }
        return result;
    }
}

