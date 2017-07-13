package com.teamplantpower.activities;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.teamplantpower.team_plant_power.Database;
import com.teamplantpower.team_plant_power.R;
import com.teamplantpower.team_plant_power.Range;
import com.teamplantpower.team_plant_power.Temperature;

public class TemperatureDisplay extends AppCompatActivity {

    private Temperature temperatureUI = new Temperature();
    Range temperatureRange = new Range("temperature", Double.NaN, Double.NaN);
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference firebaseReference;

    TextView celciusValue;
    TextView farenheitValue;
    EditText minimumValue;
    EditText maximumValue;


    /**
     * Run on activity open.
     * @param savedInstanceState The app instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_temperature_display);
        celciusValue = (TextView) findViewById(R.id.celciusValue);
        farenheitValue = (TextView) findViewById(R.id.fahrenheitValue);
        minimumValue = (EditText) findViewById(R.id.setMinTemp);
        maximumValue = (EditText) findViewById(R.id.setMaxTemp);


        //get range
        firebaseReference = database.getReference("range/temperature");
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                temperatureRange = dataSnapshot.getValue(Range.class);
                minimumValue.setText("" + temperatureRange.getMinRange());
                maximumValue.setText("" + temperatureRange.getMaxRange());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        //get temperature
        firebaseReference = database.getReference("currentTemperature");
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //remove any characters like letters or symbols
                double temp = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
                temperatureUI.setCelciusValue(temp);

                celciusValue.setText("" + temperatureUI.getCelciusValue());
                farenheitValue.setText("" +temperatureUI.getFarenheitValue());

                checkInRange();//must go here otherwise will check before data is in

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



    }



    /**
     * Set the new min and max inputs
     * @param v The current view
     */
    public void setRange(View v) {
        minimumValue = (EditText) findViewById(R.id.setMinTemp);
        maximumValue = (EditText) findViewById(R.id.setMaxTemp);

        String maxString = maximumValue.getText().toString();
        String minString = minimumValue.getText().toString();

        if (!maxString.equals("Max") && !minString.equals("Min")) {
            Range newRange = new Range("Temperature", Double.parseDouble(minString), Double.parseDouble(maxString));
            if(newRange.validateRange()){
                temperatureRange.setMinRange(newRange.getMinRange());
                temperatureRange.setMaxRange(newRange.getMaxRange());
            }
            else{
                minimumValue.setText("Min");
                maximumValue.setText("Max");
                temperatureRange.resetRange();
            }
            firebaseReference = database.getReference("range");
            firebaseReference.child(temperatureRange.getType()).setValue(temperatureRange);

        }
        checkInRange();
    }

    /**
     * Check whether temperature is in range and change text colour accordingly.
     */
    public void checkInRange(){
        if (temperatureRange.isRangeSet() && !temperatureRange.isInRange(temperatureUI.getCelciusValue())) {
            farenheitValue.setTextColor(Color.parseColor("#FF0000"));
            celciusValue.setTextColor(Color.parseColor("#FF0000"));

        } else {
            farenheitValue.setTextColor(Color.parseColor("#000000"));
            celciusValue.setTextColor(Color.parseColor("#000000"));

        }
    }
}
