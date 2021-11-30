package com.example.oeasystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.oeasystem.questiontypes.MultipleChoicesDF;
import com.example.oeasystem.questiontypes.OpenEndedDF;
import com.example.oeasystem.questiontypes.TrueFalseDF;
import com.example.oeasystem.sqlite.MyDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ListActivity extends AppCompatActivity implements TrueFalseDF.onTFDialogFragmentBtnSelected , MultipleChoicesDF.onFragmentBtnSelected, OpenEndedDF.onFragmentBtnSelected {

    String name,number;
    FloatingActionButton fab;
    ListView listView;
    ArrayList<LvItem> arrayList = new ArrayList<>();
    TrueFalseDF trueFalseDF;
    MultipleChoicesDF multipleChoicesDF;
    OpenEndedDF openEndedDF;

    ContectAdapter contectAdapter;

    //Time and date
    Button btndate, btntime;
    TextView text_date,text_time;

    //integer variable
    int cyear,cmonth,cday;
    int chour,cminute;



    //tried smt
    Button examsave;
    MyDatabaseHelper myDB;

    Date currentdate;
    String formatterCurrentDate;
    String formatterCurrentTime;
    String formattercal;
    DateFormat dayDateFormat;
    String time;
    Calendar cal;
    Date date;

    //Check Box

    CheckBox cbAndroid, cbJava, cbPhp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.lv);
        fab = findViewById(R.id.fab);

        contectAdapter = new ContectAdapter(arrayList,ListActivity.this);
        listView.setAdapter(contectAdapter);

        trueFalseDF = new TrueFalseDF();
        multipleChoicesDF = new MultipleChoicesDF();
        openEndedDF = new OpenEndedDF();

        //time and date
        cal = Calendar.getInstance();

        DateFormat timeDateFormat = new SimpleDateFormat("HH:mm:ss");
        dayDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        btndate = findViewById(R.id.btn_date);
        btntime = findViewById(R.id.btn_time);

        text_date = findViewById(R.id.date_tw);
        text_time = findViewById(R.id.time_tw);

        //For CheckBox
        cbAndroid = findViewById(R.id.cb_android);
        cbJava = findViewById(R.id.cb_java);
        cbPhp = findViewById(R.id.cb_php);

        cbAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbAndroid.isChecked()) {
                    cbAndroid.setTextColor(getResources().getColor(R.color.colorcbclicked));
                }else
                    cbAndroid.setTextColor(getResources().getColor(R.color.colorcb));

            }
        });

        cbJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbJava.isChecked()) {
                    cbJava.setTextColor(getResources().getColor(R.color.colorcbclicked));
                }else
                    cbJava.setTextColor(getResources().getColor(R.color.colorcb));

            }
        });

        cbPhp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbPhp.isChecked()) {
                    cbPhp.setTextColor(getResources().getColor(R.color.colorcbclicked));
                }else
                    cbPhp.setTextColor(getResources().getColor(R.color.colorcb));

            }
        });

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(ListActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //for textview shown
                        if ((year==2021) ){
                            Toast.makeText(getApplicationContext(),"you cant select the date ",Toast.LENGTH_LONG).show();
                        }else
                            text_date.setText(dayOfMonth+"/"+(month+1)+ "/"+year);

                        System.out.println("cyear, cmonth, cday "+ year +"/" + month +"/" + dayOfMonth );
                        cal.set(year,month,dayOfMonth);
                        date = cal.getTime();
                        System.out.println("DATE : " + date.toString() );
                        formattercal = dayDateFormat.format(date);
                        System.out.println("DATE : " + dayDateFormat.format(date));

                        if(isContain(formattercal)){
                            System.out.println("YESSSSSS");
                            new AlertDialog.Builder(ListActivity.this)
                                    .setTitle("DATE CONFLICT")
                                    .setIcon(R.drawable.ic_error)
                                    .setMessage("You can't choose the date!")
                                    .setCancelable(false)
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                        }else{
                            System.out.println("NOOOOO");
                        }





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
                TimePickerDialog timePickerDialog= new TimePickerDialog(ListActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //for time textview show
                        if ((hourOfDay==11) ||  (minute==11)){
                            Toast.makeText(getApplicationContext(),"you cant select the time ",Toast.LENGTH_LONG).show();
                        }
                        text_time.setText(hourOfDay + ":" + minute);
                        time = hourOfDay + ":" + minute ;

                    }
                },chour,cminute,false);
                timePickerDialog.show();
            }
        });


        //tried smth
        examsave = findViewById(R.id.btnexamsave);

        final Calendar cal = Calendar.getInstance();
        System.out.println(timeDateFormat.format(cal.getTime()));
        currentdate = cal.getTime();

        formatterCurrentDate = dayDateFormat.format(currentdate);
        formatterCurrentTime = timeDateFormat.format(currentdate);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = new GregorianCalendar();

        cal1.set(2010, 12, 1);
        cal2.set(2011, 9, 31);



        //formattercal = dayDateFormat.format(cal1);
        //System.out.println("CAL " + cal1.getTime());
       // System.out.println("Days= "+daysBetween(currentdate,cal2.getTime()));

        myDB = new MyDatabaseHelper(ListActivity.this);
        // this.transmitterButton = findViewById(R.id.button);
        System.out.println(".MainActivity get first date "+ getFirstData());
        myDB.deleteSpecificRows("Thu Sep 10 09:27:56 GMT+03:00 2020");
        //Toast.makeText(MainActivity.this, "Deleted succesfull", Toast.LENGTH_SHORT).show();
        long oneDayLongValue = 1000 * 60 * 60 * 24;
        System.out.println(".MainActivity get first date second time"+ getFirstData());
        Date oneDayLaterDate = new Date(currentdate.getTime() - 14*(oneDayLongValue));
        String formatteroneDayLaterDate = dayDateFormat.format(oneDayLaterDate);
        System.out.println("One day later date is ,"+ dayDateFormat.format(oneDayLaterDate));

        //System.out.println("True or false = "+ isContain("94339309-bfe2-4807-b747-9aee23513669", formatteroneDayLaterDate.trim()));

        examsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               myDB.addExam("sınavvv",formattercal.trim(),time.trim());
                Intent intent = new Intent(ListActivity.this,UuidRecyclerViewActivity.class);
                startActivity(intent);
            }
        });



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ListActivity.this);
                dialog.setContentView(R.layout.fabitem);

                final String[] questionType = {" "};
                final EditText etname = dialog.findViewById(R.id.etname);
                final EditText etnumber = dialog.findViewById(R.id.etnumber);
                Spinner spinner = dialog.findViewById(R.id.spinner1);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ListActivity.this,R.array.questiontypes,android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                        String text = parent.getItemAtPosition(position).toString();
                        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
                        questionType[0] =text;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                Button btnsave = dialog.findViewById(R.id.btnsave);

                btnsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(questionType[0].equals("True False Question")){
                            Toast.makeText(getApplicationContext(),"True False Question",Toast.LENGTH_SHORT).show();
                            trueFalseDF.setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_DialogWhenLarge);
                            trueFalseDF.show(getSupportFragmentManager(),"MyTrueFalseDialogFragment");
                        }

                        if(questionType[0].equals("Multiple Choices Question")) {
                            Toast.makeText(getApplicationContext(),"Multiple Choices Question",Toast.LENGTH_SHORT).show();

                            multipleChoicesDF.setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_DialogWhenLarge);
                            multipleChoicesDF.show(getSupportFragmentManager(),"MyMultipleChoicesDialogFragment");
                        }

                        if(questionType[0].equals("Open Ended Question")) {
                            Toast.makeText(getApplicationContext(),"Open Ended Question",Toast.LENGTH_SHORT).show();

                            openEndedDF.setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_DialogWhenLarge);
                            openEndedDF.show(getSupportFragmentManager(),"MyOpenEndedChoicesDialogFragment");
                        }


                        name = etname.getText().toString();
                        number = etnumber.getText().toString();

                        LvItem lvItem = new LvItem();
                        lvItem.setName(name);
                        lvItem.setNumber(number);
                        arrayList.add(lvItem);
                        dialog.dismiss();

                        //ContectAdapter contectAdapter = new ContectAdapter(arrayList,ListActivity.this);
                        //ContectAdapter contectAdapter = new ContectAdapter(arrayList,ListActivity.this);
                        //listView.setAdapter(contectAdapter);


                    }
                });

                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

            }
        });

   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           Toast.makeText(getApplicationContext(),"You clicked item name : " + contectAdapter.getItem(i),Toast.LENGTH_SHORT).show();
       }
   });

   listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
       @Override
       public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
           final int which_item = i ;
           new AlertDialog.Builder(ListActivity.this)
                   .setIcon(android.R.drawable.ic_delete)
                   .setTitle("Are you sure?")
                   .setMessage("Do you want to delete this item?")
                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           arrayList.remove(which_item);
                           contectAdapter.notifyDataSetChanged();
                       }
                   })
                   .setNegativeButton("No", null)
                   .show();

           return true;
       }
   });
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onDialogButtonSelected() {
        Toast.makeText(getApplicationContext(),"Button is running ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fourchoices() {
        Toast.makeText(getApplicationContext(),"Four Choices Multiple Dialog Button is running ",Toast.LENGTH_SHORT).show();
        multipleChoicesDF.getView().findViewById(R.id.ebtn).setVisibility(View.INVISIBLE);
        multipleChoicesDF.getView().findViewById(R.id.echoice).setVisibility(View.INVISIBLE);
    }

    @Override
    public void fivechoices() {
        Toast.makeText(getApplicationContext(),"Five Choices Multiple Dialog Button is running ",Toast.LENGTH_SHORT).show();
        multipleChoicesDF.getView().findViewById(R.id.ebtn).setVisibility(View.VISIBLE);
        multipleChoicesDF.getView().findViewById(R.id.echoice).setVisibility(View.VISIBLE);
    }

    @Override
    public void onTFDialogButtonSelected() {
        Toast.makeText(getApplicationContext(),"TF Dialog Button is running ",Toast.LENGTH_SHORT).show();
        trueFalseDF.getView().findViewById(R.id.truebtn).setVisibility(View.INVISIBLE);
        //trueFalseDF.dismiss();

    }

    @Override
    public void dort() {
        trueFalseDF.getView().findViewById(R.id.truebtn).setVisibility(View.VISIBLE);
    }


    @Override
    public void onOESaveButtonSelected() {
        openEndedDF.dismiss();
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
    boolean isContain( String date) {
        Cursor cursor = myDB.readSpecificDate(date);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }
}