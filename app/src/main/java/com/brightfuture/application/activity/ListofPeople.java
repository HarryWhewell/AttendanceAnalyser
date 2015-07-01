package com.brightfuture.application.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.brightfuture.application.adapters.PeopleListAdapter;
import com.brightfuture.application.persistence.Person;
import com.example.application.R;

import java.util.List;

public class ListofPeople extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_people);

        //Gets all individuals and puts them in the list 'peeps'
        List peeps = Person.listAll(Person.class);

        // Get the ListView
        ListView listView = (ListView) findViewById(R.id.list);

        //Set 'peeps' to ListView
        PeopleListAdapter activityListAdapter = new PeopleListAdapter(this, R.layout.peoplelistadapter, peeps);
        listView.setAdapter(activityListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listof_people, menu);
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
