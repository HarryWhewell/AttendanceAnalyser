package com.brightfuture.application.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.brightfuture.application.persistence.Person;
import com.example.application.R;
import com.brightfuture.application.dao.RegisteredAccountDao;

public class HomeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /* Load user preferences */
        RegisteredAccountDao.sharedPreferences = getSharedPreferences(RegisteredAccountDao.MY_PREFERENCES, Context.MODE_PRIVATE);
        displayHeaderMessage();


    }

    public void addPeople() {
        Log.d("Person 1 in", "PErson one");
        Person aperson = new Person(" kjckjnkj", "sksdkjnds");
        aperson.save();
        Person bperson = new Person("test", "testington");
        bperson.save();

        //  long numberOfBooks = Person.count(Person.class, null, null);
        // Toast.makeText(getApplicationContext(), numberOfBooks + "amount", Toast.LENGTH_LONG).show();

        Log.d("Person 1 done", "PErson one");

    }

    public void displayHeaderMessage() {
        TextView welcomeMessageView = (TextView) findViewById(R.id.homeText);
        welcomeMessageView.setText("Welcome " + RegisteredAccountDao.getEmailFromPreferences());
    }

    public void personBtn(View view) {

        Intent intent = new Intent(HomeActivity.this, ListofPeople.class);
        startActivity(intent);
    }

    public void addPersonBtn(View view){

        Intent intent = new Intent(HomeActivity.this, AddPerson.class);
        startActivity(intent);
    }

    public void close(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, you want to logout?");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int number) {
                /* Clear preferences after logout */
                SharedPreferences.Editor editor = RegisteredAccountDao.sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void onBackPressed() {
    }
}
