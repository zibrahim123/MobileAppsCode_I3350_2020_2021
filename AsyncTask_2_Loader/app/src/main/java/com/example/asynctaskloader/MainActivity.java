package com.example.asynctaskloader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    // Unique id for loader
    private static final int loader_id = 1;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportLoaderManager().initLoader(loader_id, null, this);
        tv = findViewById(R.id.txtview);
    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {

        return new MyFirstLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        tv.setText("Data are ready");
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
    }


}