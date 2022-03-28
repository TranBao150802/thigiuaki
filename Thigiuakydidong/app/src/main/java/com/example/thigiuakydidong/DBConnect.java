package com.example.thigiuakydidong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnect extends SQLiteOpenHelper {

    public DBConnect(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL("create Table Userdetails(name TEXT primary key, contact TEXT, email TEXT, address TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop Table if exists Userdetails");
    }

    public Boolean insserusertdata(String name, String email, String contact, String address) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("email", email);
        contentValues.put("address", address);
        long result=sqLiteDatabase.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean updateusertdata(String name, String email, String contact, String address) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("email", email);
        contentValues.put("address", address);
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Userdetails where name=?", new String[] {name});
        if(cursor.getCount()>0) {
            long result = sqLiteDatabase.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Boolean deletedata(String name) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Userdetails where name=?", new String[] {name});
        if(cursor.getCount()>0) {
            long result = sqLiteDatabase.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Userdetails", null);
        return cursor;
    }

}
