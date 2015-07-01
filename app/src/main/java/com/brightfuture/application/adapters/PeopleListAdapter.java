package com.brightfuture.application.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.brightfuture.application.persistence.Person;
import com.example.application.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BJohnson on 30/06/2015.
 */
public class PeopleListAdapter extends ArrayAdapter<Person> {
        Context context;
        List<Person> listItems = new ArrayList<Person>();
        int textViewResourceId;

//Getting all of the resources in which the adapter needs
public PeopleListAdapter(Context context, int textViewResourceId, List<Person> listItems) {
        super(context, textViewResourceId, listItems);
        this.context = context;
        this.listItems = listItems;
        this.textViewResourceId = textViewResourceId;

        }

// Setting the view of the adapter
@Override
public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) ((Activity) context)
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi = inflater.inflate(textViewResourceId, parent, false);

        TextView mFirstName = (TextView) vi.findViewById(R.id.firstName);
        TextView mLastName = (TextView) vi.findViewById(R.id.lastName);

        mFirstName.setText(listItems.get(position).getFirstName().toString());
        mLastName.setText(listItems.get(position).getLastName().toString());
        return vi;

        }

// This returns the position of the adapter
@Override
public int getCount() {
        return (this.listItems != null) ? this.listItems.size() : 0;
        }
}
