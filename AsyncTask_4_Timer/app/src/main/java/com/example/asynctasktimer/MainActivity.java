package com.example.asynctasktimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtTimer;
    AsyncTaskTimer ast;
    Button btnStart, btnStop, btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTimer = findViewById(R.id.textViewTimer);
        btnStart = findViewById(R.id.startBtn);
        btnStop = findViewById(R.id.stopBtn);
        btnReset = findViewById(R.id.resetBtn);
        btnStop.setEnabled(false);
        btnReset.setEnabled(false);
    }

    public void startTimer(View v){
        ast = new AsyncTaskTimer();
        ast.execute(Integer.parseInt(txtTimer.getText().toString()));
        btnStop.setEnabled(true);
        btnStart.setEnabled(false);
        btnReset.setEnabled(false);
    }

    public void stopTimer(View v){
        ast.cancel(true);
        btnStop.setEnabled(false);
        btnReset.setEnabled(true);
        btnStart.setEnabled(true);
    }

    public void resetTimer(View v){
        txtTimer.setText("0");
        btnReset.setEnabled(false);
        btnStop.setEnabled(false);
        btnStart.setEnabled(true);
    }

    private class AsyncTaskTimer extends AsyncTask<Integer,Integer,Void>{

        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this,"Started timer",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            txtTimer.setText(""+values[0]);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int v = integers[0];
            //int x = Integer.parseInt(txtTimer.getText().toString());

            while(! isCancelled()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(++v);
            }
            return null;
        }
    }

}