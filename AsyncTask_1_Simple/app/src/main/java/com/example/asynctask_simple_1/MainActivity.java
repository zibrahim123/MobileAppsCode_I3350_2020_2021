package com.example.asynctask_simple_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.pBAsync);
    }

    public void startTask(View view)
    {
        LongOperations op = new LongOperations();
        progressBar.setProgress(0);
        op.execute(5000000); // argument sent to doInBackground

    }

    private class LongOperations extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Started an AsyncTask", Toast.LENGTH_LONG).show();
        }
        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            // Update the ProgressBar
            progressBar.setProgress(values[0]);
        }
        @Override
        protected Void doInBackground(Integer... params) {

            int progress;
            for (progress=0;progress<=99;progress++)
            {
                for(int i = 0; i < params[0]; i++){}
                // the publishProgress update the UI by calling onProgressUpdate
                publishProgress(progress+1);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getApplicationContext(), "Finished AsyncTask job", Toast.LENGTH_LONG).show();
        }

    }
}