package com.example.oeasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimeDateActivity extends AppCompatActivity {

    TimePicker time;
    DatePicker date;
    Button t, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_date);

        t = findViewById(R.id.timebtn);
        d = findViewById(R.id.datebtn);
        time = findViewById(R.id.timepicker);
        date = findViewById(R.id.datepicker);

        time.setIs24HourView(true);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Date: "+ (date.getMonth()+1) + "/" +date.getDayOfMonth() + "/" + date.getYear(), Toast.LENGTH_LONG ).show();
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Time: "+time.getCurrentHour() + ":" + time.getCurrentMinute(), Toast.LENGTH_LONG ).show();

            }
        });
    }
}