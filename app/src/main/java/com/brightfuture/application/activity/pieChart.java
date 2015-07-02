package com.brightfuture.application.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.brightfuture.application.persistence.Person;
import com.example.application.R;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.List;


public class pieChart extends ActionBarActivity {


    private PieChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

    List peeps;
    Person clicked;
    private Typeface tf;

    static final String PERSON_POSITION = "PERSON POSITION";
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        Intent intent = getIntent();
        position = intent.getIntExtra(BarChartActivity.PERSON_POSITION, 0);
        peeps = Person.listAll(Person.class);
        clicked = (Person) peeps.get(position);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        TextView tv = (TextView) findViewById(R.id.chartTitle);
        tv.setText(clicked.getFirstName() + "" + "Attendance");
        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.setDescription(clicked.getFirstName() + "Attendance");

        mChart.setDragDecelerationFrictionCoef(0.95f);


        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);

        mChart.setTransparentCircleColor(Color.WHITE);

        mChart.setHoleRadius(60f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setCenterText(clicked.getFirstName() + "" + clicked.getLastName() + "\n2014/2015 Attendance");

        setData();

        mChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);
    }



    private void setData() {



        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        yVals1.add(new Entry(clicked.getAttended(),1));
        yVals1.add(new Entry(clicked.getAuthorisedAbsences(),1));
        yVals1.add(new Entry(clicked.getUnauthorisedAnsences(),1));



        ArrayList<String> xVals = new ArrayList<String>();


        xVals.add("Attended");
        xVals.add("Authorised Absences");
        xVals.add("Unauthorised Absences");


        PieDataSet dataSet = new PieDataSet(yVals1, "Attendance ");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();



        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(tf);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }
    public void BarBTn(View view){
        Intent intent = new Intent(pieChart.this,BarChartActivity.class);
        intent.putExtra(PERSON_POSITION, position);
        startActivity(intent);
    }

}