package com.example.oeasystem.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Exam.db"; //Constant
    private static final int DATABASE_VERSION = 1; //Constant

    private static final String TABLE_NAME = "exam_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "exam_title";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_TIME + " TEXT)";

        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addExam(String exam_title, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, exam_title);
        cv.put(COLUMN_DATE, String.valueOf(date));
        cv.put(COLUMN_TIME, String.valueOf(time));

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Succesfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readFirstDate() {

        String query = "SELECT MIN(" + COLUMN_DATE + ") FROM " + TABLE_NAME;
        //String query = "SELECT top1  FROM " + TABLE_NAME + " order by " + COLUMN_ID + " desc" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;

    }


    public Cursor readLastDate() {

        String query = "SELECT MAX(" + COLUMN_DATE + ") FROM " + TABLE_NAME;
        //String query = "SELECT top1  FROM " + TABLE_NAME + " order by " + COLUMN_ID + " desc" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;

    }

    //I created delete the specific date records in the table
    public void deleteSpecificRows(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "date=?", new String[]{date});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }


    public Cursor readSpecificData(String exam_title, String date) {

        String query = "SELECT " + COLUMN_TITLE + " FROM " + TABLE_NAME + " WHERE " + COLUMN_TITLE + " = '" + exam_title + "' AND " + COLUMN_DATE + " = '" + date + "'";
        //String query = "SELECT top1  FROM " + TABLE_NAME + " order by " + COLUMN_ID + " desc" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
            if (cursor != null) {
                Toast.makeText(context, "No data again! ", Toast.LENGTH_SHORT).show();
            }
        }
        return cursor;

    }

    public Cursor readSpecificDate(String date) {

        String query = "SELECT " + COLUMN_DATE + " FROM " + TABLE_NAME + " WHERE " + COLUMN_DATE + " = '" + date + "'";
        //String query = "SELECT top1  FROM " + TABLE_NAME + " order by " + COLUMN_ID + " desc" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
            if (cursor != null) {
                Toast.makeText(context, "No data again! ", Toast.LENGTH_SHORT).show();
            }
        }
        return cursor;

    }

}
