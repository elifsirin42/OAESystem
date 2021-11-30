package com.example.oeasystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.oeasystem.sqlite.CustomAdapter;
import com.example.oeasystem.sqlite.MyDatabaseHelper;

import java.util.ArrayList;

public class UuidRecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;

    ArrayList<String> id, uuid_title, date, time;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uuid_recycler_view);

        recyclerView = findViewById(R.id.recyclerview);

        myDB = new MyDatabaseHelper(UuidRecyclerViewActivity.this);
        id = new ArrayList<>();
        uuid_title = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(UuidRecyclerViewActivity.this, id, uuid_title, date, time);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(UuidRecyclerViewActivity.this));
        Toast.makeText(this, "current date is " + getFirstData(), Toast.LENGTH_SHORT).show();
        System.out.println("current date is " + getFirstData());
        // System.out.println(".UuidRecyclerVİewActivty isContain result " + isContain("1"));
    }

/*
    boolean isContain(String uuid) {
        Cursor cursor = myDB.readSpecificData(uuid);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                uuid_title.add(cursor.getString(1));
                date.add(cursor.getString(2));
            }
            return true;
        }

    }*/


    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                uuid_title.add(cursor.getString(1));
                date.add(cursor.getString(2));
                time.add(cursor.getString(3));
            }
        }
    }

    String getFirstData() {
        Cursor cursor = myDB.readFirstDate();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            return "No data";
        } else {
            while (cursor.moveToNext()) {
                // id.add(cursor.getString(0));
                // uuid_title.add(cursor.getString(1));
                // date.add(cursor.getString(2));
                String date = cursor.getString(0);
                return date;
            }
        }
        return "No data to show";
    }

}
