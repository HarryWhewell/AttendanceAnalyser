package com.brightfuture.application.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import com.example.application.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import com.brightfuture.application.persistence.Person;

public class BarChartActivity extends ActionBarActivity {
    List peeps;
    Person clicked;
    static final String PERSON_POSITION = "PERSON POSITION";
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);


        // Get position of Person selected in ListOfPeople
        Intent intent = getIntent();
        position = intent.getIntExtra(ListofPeople.PERSON_POSITION, 0);
        peeps = Person.listAll(Person.class);
        clicked = (Person) peeps.get(position);

        String firstName = clicked.getFirstName();
        TextView chartTitle = (TextView) findViewById(R.id.chartTitle);
        chartTitle.setText(firstName + " Attendance");
        BarChart chart = (BarChart) findViewById(R.id.chart);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription(firstName + " Attendance");
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }
    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(clicked.getAttended(), 0); // Attended
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(clicked.getAuthorisedAbsences(), 1); // Absent
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(clicked.getUnauthorisedAnsences(), 2); // Authorised absent
        valueSet1.add(v1e3);
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Attended");
        xAxis.add("Absent");
        xAxis.add("Authorised absent");

        return xAxis;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar_chart, menu);
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

    public void pieBTN(View view){
        Intent intent = new Intent(BarChartActivity.this,pieChart.class);
        intent.putExtra(PERSON_POSITION, position);
        startActivity(intent);
    }
}
