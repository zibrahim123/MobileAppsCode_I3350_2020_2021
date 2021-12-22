package com.example.flagquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private AssetManager assets;
    private Button btn1, btn2, btn3;
    private TextView txtQuestionCounter, txtResult;
    private String[] flags_names;
    private ImageView img;
    private String correct_name="";
    private int currentQuestion=0, correctQuestion=0;
    private boolean todisable = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assets = this.getAssets();
        try {
            flags_names = assets.list("png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        img = findViewById(R.id.imageView);
        txtQuestionCounter = findViewById(R.id.txtQuestionCounter);
        txtResult = findViewById(R.id.txtResult);
        fillQuestion();
        //method to fill the first question
    }
    public void fillQuestion() {
        currentQuestion++;
        if (currentQuestion == 11) {
            Intent i = new Intent(this, ResltActivity.class);
            i.putExtra("correct",String.valueOf(correctQuestion));
            startActivity(i);
        } else {
            txtQuestionCounter.setText("Question " + currentQuestion + " / 10");
            txtResult.setText("");
            int number_flags = flags_names.length;
            int v1 = (int) (Math.random() * number_flags);
            int v2 = (int) (Math.random() * number_flags);
            int v3 = (int) (Math.random() * number_flags);
            int v = ((int) (Math.random() * 3)) + 1;
            String v1_name = flags_names[v1].split("-")[1];
            String v2_name = flags_names[v2].split("-")[1];
            String v3_name = flags_names[v3].split("-")[1];
            Drawable dr = null;
            try {
                dr = Drawable.createFromStream(assets.open("png/" + flags_names[v1]), v1_name);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            img.setImageDrawable(dr);
            if (v == 1) {
                btn1.setText(v1_name.split(".png")[0]);
                btn2.setText(v2_name.split(".png")[0]);
                btn3.setText(v3_name.split(".png")[0]);
            } else if (v == 2) {
                btn1.setText(v2_name.split(".png")[0]);
                btn2.setText(v1_name.split(".png")[0]);
                btn3.setText(v3_name.split(".png")[0]);
            } else {
                btn1.setText(v2_name.split(".png")[0]);
                btn2.setText(v3_name.split(".png")[0]);
                btn3.setText(v1_name.split(".png")[0]);
            }
            correct_name = v1_name.split(".png")[0];
            btn1.setEnabled(true);
            btn2.setEnabled(true);
            btn3.setEnabled(true);
            todisable = true;
            invalidateOptionsMenu();
        }
    }

    public void check_answer(View v){
        String value = ((Button) v).getText().toString();
        if(value.equals(correct_name)) {
            txtResult.setText("Correct Answer");
            txtResult.setTextColor(Color.GREEN);
            correctQuestion ++;
        }
        else {
            txtResult.setText("Wrong Answer\nCorrect Answer : " + correct_name);
            txtResult.setTextColor(Color.RED);
        }
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        todisable = false;
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_next) {
            fillQuestion();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        if (todisable) {
            menu.getItem(0).setEnabled(false);
        }
        else {
            menu.getItem(0).setEnabled(true);
        }
        return true;
    }
}