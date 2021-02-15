package com.example.asynctaskloader;

import android.content.Context;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

public class MyFirstLoader extends AsyncTaskLoader<String> {
    private int counter = 0;
    private String result=null;
    private static final String TAG = "MyMainActivity";

    public MyFirstLoader(Context context) {
        super(context);
    }

    @Override
    public String loadInBackground() {
        // Some work, e.g. load something from internet
        try {
            for(int i = 0; i<10; i++) {
                Thread.sleep(1000);
                Log.i(TAG, "second " + i + " / 10");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OK";
    }

    @Override
    public void deliverResult(String data) {

        result = data;
        // Deliver result if loader is currently started
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        // Start loading
        //here we force loading the data or take the cached one if we have store them during the call of deliver result
        if(result == null)
            forceLoad();
        else
            super.deliverResult(result);
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();
    }
}