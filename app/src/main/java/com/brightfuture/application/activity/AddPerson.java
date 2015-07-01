package com.brightfuture.application.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.application.R;
import com.brightfuture.application.persistence.Person;
public class AddPerson extends ActionBarActivity {

    EditText mFirstName;
    EditText mLastName;
    EditText mAttended;
    EditText mAuthorised;
    EditText mUnauthorised;

    String firstName;
    String lastName;
    int Attended;
    int Authorised;
    int Unauthorised;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        mFirstName = (EditText) findViewById(R.id.etFirstName);
        mLastName = (EditText) findViewById(R.id.etLastName);
        mAttended = (EditText) findViewById(R.id.etAttended);
        mAuthorised = (EditText) findViewById(R.id.etAuthorised);
        mUnauthorised = (EditText) findViewById(R.id.etUnauthorised);
    }


    public void addPerson (View view){

        // Get input from EditText fields
        firstName = mFirstName.getText().toString();
        lastName = mLastName.getText().toString();
        Attended = Integer.parseInt(mAttended.getText().toString());
        Authorised = Integer.parseInt(mAuthorised.getText().toString());
        Unauthorised = Integer.parseInt(mUnauthorised.getText().toString());
        // Set person to model
        Person person = new Person(firstName,lastName,Attended,Authorised,Unauthorised);
        person.save();

        // Clear EditText fields
        mFirstName.setText("");
        mLastName.setText("");
        mAttended.setText("");
        mAuthorised.setText("");
        mUnauthorised.setText("");

        Toast.makeText(getApplicationContext(), "Person Added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
