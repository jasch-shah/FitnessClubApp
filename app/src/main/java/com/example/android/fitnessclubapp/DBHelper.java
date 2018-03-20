package com.example.android.fitnessclubapp;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by jashshah on 20/03/18.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "exercise";
    public static final String TABLE_NAME = "history";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "exercise_name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String CREATE_TABLE =
            "CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_NAME+" TEXT NOT NULL, " +
                    COLUMN_DATE+" TEXT NOT NULL, " +
                    COLUMN_TIME+" TEXT NOT NULL);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertRecord(HistoryObject object){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,object.getName());
        cv.put(COLUMN_DATE,object.getDate());
        cv.put(COLUMN_TIME,object.getTotalTime());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,cv);
    }
    public ArrayList<HistoryObject> returnAllRecords(){
        ArrayList<HistoryObject> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {COLUMN_NAME,COLUMN_DATE,COLUMN_TIME};
        String sortOrder = COLUMN_ID + " DESC";
        Cursor c = db.query(TABLE_NAME,columns,null,null,null,null,sortOrder);
        c.moveToFirst();
        while(!c.isAfterLast()){
            HistoryObject object = new HistoryObject(c.getString(c.getColumnIndex(COLUMN_NAME)),
                    c.getString(c.getColumnIndex(COLUMN_DATE)),
                    c.getString(c.getColumnIndex(COLUMN_TIME)));
            list.add(object);
            c.moveToNext();
        }
        return list;
    }
    public void deleteHistory(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }
}
