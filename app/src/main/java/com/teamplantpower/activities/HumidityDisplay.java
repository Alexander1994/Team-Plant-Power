package com.teamplantpower.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamplantpower.team_plant_power.Database;
import com.teamplantpower.team_plant_power.Humidity;
import com.teamplantpower.team_plant_power.R;
import com.teamplantpower.team_plant_power.Range;


public class HumidityDisplay extends AppCompatActivity {
    private static String TAG = "HumidityDisplay";
    //UI Elements
    private Humidity humidityUI = new Humidity();
    Range humidityRange = new Range("humidity", 0, 100);
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference firebaseReference;

    TextView humidityExposureValue;
    EditText minimumValue;
    EditText maximumValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity_display);
        humidityExposureValue = (TextView) findViewById(R.id.humidityExposureValue);
        minimumValue = (EditText) findViewById(R.id.setMinHumidity);
        maximumValue = (EditText) findViewById(R.id.setMaxHumidity);


        //get range
        firebaseReference = database.getReference("range/humidity");
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                humidityRange = dataSnapshot.getValue(Range.class);
                minimumValue.setText("" + humidityRange.getMinRange());
                maximumValue.setText("" + humidityRange.getMaxRange());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        //get humidity
        firebaseReference = database.getReference("currentHumidity");
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //remove any characters like letters or symbols
                double humidityx = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
                humidityUI.setPercentHumidity(humidityx);
                humidityExposureValue.setText("" + humidityUI.getPercentHumidity());

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
     * Run when historical button tapped
     * @param v The current view
     */
    public void openHistorical(View v) {
        Intent intent = new Intent(this, HistoricalHumidity.class);
        startActivity(intent);

    }


    public void setRange(View v) {
        minimumValue = (EditText) findViewById(R.id.setMinHumidity);
        maximumValue = (EditText) findViewById(R.id.setMaxHumidity);

        String maxString = maximumValue.getText().toString();
        String minString = minimumValue.getText().toString();

        if (!maxString.equals("Max") && !minString.equals("Min")) {
            Range newRange = new Range("Humidity", Double.parseDouble(minString), Double.parseDouble(maxString));
            if(newRange.validateRange()){
                humidityRange.setMinRange(newRange.getMinRange());
                humidityRange.setMaxRange(newRange.getMaxRange());
            }
            else{
                minimumValue.setText("Min");
                maximumValue.setText("Max");
                humidityRange.resetRange();
            }
            firebaseReference = database.getReference("range");
            firebaseReference.child(humidityRange.getType()).setValue(humidityRange);

        }
        checkInRange();
    }


    public void checkInRange(){
        if (humidityRange.isRangeSet() && !humidityRange.isInRange(humidityUI.getPercentHumidity())) {
            humidityExposureValue.setTextColor(Color.parseColor("#FF0000"));

        } else {
            humidityExposureValue.setTextColor(Color.parseColor("#000000"));

        }
    }




}










