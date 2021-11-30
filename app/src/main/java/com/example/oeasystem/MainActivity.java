package com.example.oeasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "com.example.oeasystem.MESSAGE";

    EditText emailet;
    EditText passwordet;
    Button loginbtn;

    List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating list of User
        list = new ArrayList<User>();

        //Creating Users
        User u1 = new User(1, "elif", "1234", "student");
        User u2 = new User(2, "merve", "456", "student");
        User u3 = new User(3, "ayşe", "789", "teacher");

        list.add(u1);
        list.add(u2);
        list.add(u3);

        /*userspasswords = new HashMap<String, String>();

        // Add keys and values (User, Password)
        userspasswords.put("elif","1234");
        userspasswords.put("merve","1234");
        System.out.println(userspasswords.get("elif"));
*/
        emailet = findViewById(R.id.editTextEmail);
        passwordet = findViewById(R.id.editTextPassword);
        loginbtn = findViewById(R.id.buttonlogin);
        loginbtn.setOnClickListener(this);

    }

    //I don't want to back when i press back button on the phone
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonlogin:
                System.out.println("I am hereee");
                //do something
                //traversing list
                Intent intent = null;
                for (User u : list) {
                    if (emailet.getText().toString().equals(u.getUserName())) {
                        System.out.println("IFFF I am hereee");
                        System.out.println(u.getUserPassword().toString());
                        Toast.makeText(getApplicationContext(), u.getUserPassword().toString(), Toast.LENGTH_LONG).show();
                        if (passwordet.getText().toString().equals(u.getUserPassword())) {
                            Toast.makeText(getApplicationContext(), "Login succesfull", Toast.LENGTH_LONG).show();
                            System.out.println("Login succesfull");
                            if (u.UserType.equals("student")) {
                                intent = new Intent(this, StudentActivity.class);
                            }if (u.UserType.equals("teacher")) {
                                intent = new Intent(this, TeacherActivity.class);
                            }
                           // Toast.makeText(getApplicationContext(),  "a= " + a.toString(), Toast.LENGTH_LONG).show();
                            String message = emailet.getText().toString();
                            intent.putExtra(EXTRA_MESSAGE, message);
                            startActivity(intent);
                            }
                    Toast.makeText(getApplicationContext(), u.getUserPassword().toString(), Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                    }
                    }
                break;
            default:
                break;
        }

   }
}


   /* private void login() {
        System.out.println("I am hereee");
        //do something
        //traversing list
        for(User u:list){
            System.out.println(u.UserName +" "+ u.UserPassword);
            if(emailet.getText().toString().equals(u.UserName) && passwordet.getText().toString().equals(u.UserPassword)){
                Toast.makeText(getApplicationContext(), "Login Succesfull!", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_LONG).show();
            }

        }
    }*/
