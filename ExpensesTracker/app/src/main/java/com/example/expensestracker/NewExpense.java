package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewExpense extends AppCompatActivity {

    EditText t,d;
    Spinner c;
    Button add;
    DataBaseHandler db;
    long l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);

    }
    public void add_expene(View v){
        t=(EditText)findViewById(R.id.amount);
        d=(EditText)findViewById(R.id.desc);
        c=(Spinner) findViewById(R.id.categ);
        add=(Button)findViewById(R.id.add);
        db=new DataBaseHandler(this);
        Integer amount=Integer.parseInt(t.getText().toString());
        String des=d.getText().toString();
        String cat=c.getSelectedItem().toString();
        ExpenseManager newt= new ExpenseManager(amount,des,cat);
        db.AddExpenses(newt);
    }
}