package com.example.asynctaskloaderusers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<UserAccount>>, Loader.OnLoadCanceledListener<List<UserAccount>> {
    private static final String LOG_TAG = "AndroidExample";
    private static final int LOADER_ID_USERACCOUNT = 10000;

    private Button buttonLoad;
    private Button buttonCancel;
    private ProgressBar progressBar;
    private TextView textView;

    private static final String KEY_PARAM1 = "SomeKey1";
    private static final String KEY_PARAM2 = "SomeKey2";

    private LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonLoad = (Button) this.findViewById(R.id.btn_load);
        this.buttonCancel = (Button) this.findViewById(R.id.btn_cancel);
        this.progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
        this.textView = (TextView) this.findViewById(R.id.textView);

        // Hide ProgressBar.
        this.progressBar.setVisibility(View.GONE);
        this.buttonCancel.setEnabled(false);

        this.buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtonLoad();
            }
        });

        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtonCancel();
            }
        });

        this.loaderManager = LoaderManager.getInstance(this);
    }

    // User click on "Load Data" button.
    private void clickButtonLoad() {
        this.textView.setText("");
        Log.i(LOG_TAG, "loadUserAccount");

        // Arguments:
        Bundle args = new Bundle();
        args.putString(KEY_PARAM1, "Some value1");
        args.putString(KEY_PARAM2, "Some value2");

        // You can pass a null args to a Loader
        Loader<List<UserAccount>> loader = this.loaderManager.initLoader(LOADER_ID_USERACCOUNT, args, this);
        try {
            loader.registerOnLoadCanceledListener(this); // Loader.OnLoadCanceledListener
        } catch(IllegalStateException e) {
            // There is already a listener registered
        }
        loader.forceLoad(); // Start Loading..
    }

    // User click on "Cancel" button.
    private void clickButtonCancel() {
        Log.i(LOG_TAG, "cancelLoadUserAccount");
        Loader<List<UserAccount>> loader = this.loaderManager.getLoader(LOADER_ID_USERACCOUNT);
        if (loader != null) {
            boolean cancelled = loader.cancelLoad();
        }
    }


    @NonNull
    @Override
    public Loader<List<UserAccount>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.i(LOG_TAG, "onCreateLoader");
        this.progressBar.setVisibility(View.VISIBLE); // To show
        if (id == LOADER_ID_USERACCOUNT) {
            this.buttonLoad.setEnabled(false);
            this.buttonCancel.setEnabled(true);
            // Parameters:
            String param1 = (String) args.get(KEY_PARAM1);
            String param2 = (String) args.get(KEY_PARAM2);
            return new UserAccountTaskLoader(MainActivity.this, param1, param2);
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<UserAccount>> loader, List<UserAccount> data) {
        Log.i(LOG_TAG, "onLoadFinished");

        if (loader.getId() == LOADER_ID_USERACCOUNT) {
            // Destroy a Loader by ID.
            this.loaderManager.destroyLoader(loader.getId());

            StringBuilder sb = new StringBuilder();

            for (UserAccount userAccount : data) {
                sb.append("Username:").append(userAccount.getUserName()).append("\t") //
                        .append("Email:").append(userAccount.getEmail()).append("\n");
            }
            this.textView.setText(sb.toString());
            // Hide ProgressBar.
            this.progressBar.setVisibility(View.GONE);
            this.buttonLoad.setEnabled(true);
            this.buttonCancel.setEnabled(false);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<UserAccount>> loader) {
        Log.i(LOG_TAG, "onLoaderReset");

        this.textView.setText("");
    }

    // Implements method of Loader.OnLoadCanceledListener
    @Override
    public void onLoadCanceled(@NonNull Loader<List<UserAccount>> loader) {
        Log.i(LOG_TAG, "onLoadCanceled");

        if (loader.getId() == LOADER_ID_USERACCOUNT) {
            // Destroy a Loader by ID.
            this.loaderManager.destroyLoader(loader.getId());

            this.progressBar.setVisibility(View.GONE); // To hide
            this.buttonLoad.setEnabled(true);
            this.buttonCancel.setEnabled(false);
        }
    }
}