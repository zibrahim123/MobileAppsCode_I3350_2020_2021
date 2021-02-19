package com.example.faiizii.mysqltest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class bckground extends AsyncTask <String, Void, String> {

    AlertDialog dialog;
    Context context;
    String user,pass;
    public Boolean login = false;
    public bckground(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Login Status");

    }
    @Override
    protected void onPostExecute(final String s) {
        if(s.equals("Y")) {
            Intent intent_name = new Intent(context.getApplicationContext(),Main2Activity.class);
            context.startActivity(intent_name);
        }
        else {
            dialog.setMessage(" Login Failed: " + user);
            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", (DialogInterface.OnClickListener) null);
            dialog.show();
        }
    }
    @Override
    protected String doInBackground(String... params) {
        String result = "";
        String user = params[0];
        String pass = params[1];
        this.user = user;
        this.pass = pass;
        String connstr = "http://10.0.3.2:80/TestAndroidConnection/checkUser.php"; //for genymotion
        //String connstr = "http://192.168.1.15:8080/TestAndroidConnection/checkUser.php";//for emulator (10.0.2.2)


        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")
                    +"&&"+URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips));
            String line ="";
            while ((line = reader.readLine()) != null)
            {
                result += line;
            }
            reader.close();
            ips.close();
            http.disconnect();
            //return result;

        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }
        //dialog.setMessage(result+" - "+user+" : "+pass);
        //dialog.show();
        System.out.println("results: "+result);
        return result;
    }
}
