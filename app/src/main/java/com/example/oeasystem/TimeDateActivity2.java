package com.example.oeasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimeDateActivity2 extends AppCompatActivity {

    Button btndate, btntime;
    TextView text_date,text_time;

    //integer variable
    int cyear,cmonth,cday;
    int chour,cminute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_date2);

        btndate = findViewById(R.id.btn_date);
        btntime = findViewById(R.id.btn_time);

        text_date = findViewById(R.id.date_tw);
        text_time = findViewById(R.id.time_tw);

        //for Date picker
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //show current date
                final Calendar calendar = Calendar.getInstance();
                cyear = calendar.get(Calendar.YEAR);
                cmonth = calendar.get(Calendar.MONTH);
                cday = calendar.get(Calendar.DAY_OF_MONTH);

                //Launch datepicker Dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(TimeDateActivity2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //for textview shown
                        if ((year==2021) ){
                            Toast.makeText(getApplicationContext(),"you cant select the date ",Toast.LENGTH_LONG).show();
                        }else
                            text_date.setText(dayOfMonth+"/"+(month+1)+ "/"+year);


                    }
                },cyear,cmonth,cday);
                datePickerDialog.show();


            }
        });
        //for Time picker
        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                chour = calendar.get(Calendar.HOUR);
                cminute = calendar.get(Calendar.MINUTE);
                //Launch TimePicker Dialog
                TimePickerDialog timePickerDialog= new TimePickerDialog(TimeDateActivity2.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //for time textview show
                        if ((hourOfDay==11) ||  (minute==11)){
                            Toast.makeText(getApplicationContext(),"you cant select the time ",Toast.LENGTH_LONG).show();
                        }
                        text_time.setText(hourOfDay + ":" + minute);
                    }
                },chour,cminute,false);
                timePickerDialog.show();
            }
        });
    }
}