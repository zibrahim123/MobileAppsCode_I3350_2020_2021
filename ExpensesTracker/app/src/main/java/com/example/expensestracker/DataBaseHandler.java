package com.example.expensestracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ul on 2/1/2019.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ExpensesList";
    private static final String TABLE_EXPENSES = "Expenses";
    private SQLiteDatabase db;
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_AMOUNT = "amount"; //title of product such as computer, chair, mobile,...
    private static final String KEY_DESC= "description"; //description of the product
    private static final String KEY_CAT= "category"; // to which category it belongs such as electronics, fruits, ....
    private static final String KEY_DATE= "dateExpense"; // to which category it belongs such as electronics, fruits, ....




    public DataBaseHandler(Context c) {
        super(c, DATABASE_NAME, null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE="CREATE TABLE " + TABLE_EXPENSES   + "(" +KEY_ID +" INTEGER PRIMARY KEY," + KEY_AMOUNT + " INTEGER,"
                + KEY_DESC + " Text," + KEY_CAT + " Text," + KEY_DATE +" TEXT)";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES );
        onCreate(db);
    }
    ArrayList<String> getAllExpenses(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query=" SELECT DISTINCT category FROM " + TABLE_EXPENSES;
        Cursor cursor= db.rawQuery(query ,null);
        ArrayList<String> categoryList= new ArrayList<String>();
        if(cursor.moveToFirst()){
            do{
                categoryList.add(cursor.getString(0));

            }while (cursor.moveToNext());
            db.close();
        }
        return categoryList;
    }
    public long AddExpenses(ExpenseManager c){
        long l;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_ID, c.getId());
        values.put(KEY_AMOUNT, c.getAmount());
        values.put(KEY_DESC, c.getDescription());
        values.put(KEY_CAT, c.getCategory());
        values.put("dateExpense", new Date().toString());
        try{
            l=db.insertOrThrow(TABLE_EXPENSES,null,values);
        }
        catch(Exception ex){
            throw ex;
        }
        db.close();
        return l;
    }
    public void update (int id,int amount , String desc,String cat){

        db=this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(KEY_ID,id);
        con.put(KEY_AMOUNT,amount);
        con.put(KEY_DESC,desc);
        con.put(KEY_CAT,cat);
        db.update(TABLE_EXPENSES, con, KEY_ID + " =" + id, null);
        db.close();

    }
}

