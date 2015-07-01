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

    String firstName;
    String lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        mFirstName = (EditText) findViewById(R.id.etFirstName);
        mLastName = (EditText) findViewById(R.id.etLastName);
    }


    public void addPerson (View view){

        // Get input from EditText fields
        firstName = mFirstName.getText().toString();
        lastName = mLastName.getText().toString();

        // Set person to model
        Person person = new Person(firstName,lastName);
        person.save();

        // Clear EditText fields
        mFirstName.setText("");
        mLastName.setText("");

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
