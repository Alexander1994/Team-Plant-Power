package com.teamplantpower.activities;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.teamplantpower.team_plant_power.Database;
import com.teamplantpower.team_plant_power.Light;
import com.teamplantpower.team_plant_power.R;
import com.teamplantpower.team_plant_power.Range;



 public class LightDisplay extends AppCompatActivity {

 private Light lightUI = new Light();
 Range lightRange = new Range("light", 0, 100);
 FirebaseDatabase database = FirebaseDatabase.getInstance();
 DatabaseReference firebaseReference;

 TextView lightExposureValue;
 EditText minimumValue;
 EditText maximumValue;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_light_display);
  lightExposureValue = (TextView) findViewById(R.id.lightExposureValue);
  minimumValue = (EditText) findViewById(R.id.setMinLight);
  maximumValue = (EditText) findViewById(R.id.setMaxLight);


  //get range
  firebaseReference = database.getReference("range/light");
  firebaseReference.addValueEventListener(new ValueEventListener() {
   @Override
   public void onDataChange(DataSnapshot dataSnapshot) {
    // This method is called once with the initial value and again
    // whenever data at this location is updated.
    lightRange = dataSnapshot.getValue(Range.class);
    minimumValue.setText("" + lightRange.getMinRange());
    maximumValue.setText("" + lightRange.getMaxRange());


   }

   @Override
   public void onCancelled(DatabaseError error) {
    // Failed to read value
    //Log.w(TAG, "Failed to read value.", error.toException());
   }
  });


  //get light
  firebaseReference = database.getReference("currentLight");
  firebaseReference.addValueEventListener(new ValueEventListener() {
   @Override
   public void onDataChange(DataSnapshot dataSnapshot) {
    // This method is called once with the initial value and again
    // whenever data at this location is updated.
    String value = dataSnapshot.getValue(String.class);
    //remove any characters like letters or symbols
    double lightx = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
    lightUI.setPercentLight(lightx);
    lightExposureValue.setText("" + lightUI.getPercentLight());

    checkInRange();//must go here otherwise will check before data is in

   }



   @Override
   public void onCancelled(DatabaseError error) {
    // Failed to read value
    //Log.w(TAG, "Failed to read value.", error.toException());
   }
  });
 }


 public void setRange(View v) {
 minimumValue = (EditText) findViewById(R.id.setMinLight);
 maximumValue = (EditText) findViewById(R.id.setMaxLight);

 String maxString = maximumValue.getText().toString();
 String minString = minimumValue.getText().toString();

 if (!maxString.equals("Max") && !minString.equals("Min")) {
 Range newRange = new Range("Light", Double.parseDouble(minString), Double.parseDouble(maxString));
 if(newRange.validateRange()){
 lightRange.setMinRange(newRange.getMinRange());
 lightRange.setMaxRange(newRange.getMaxRange());
 }
 else{
 minimumValue.setText("Min");
 maximumValue.setText("Max");
 lightRange.resetRange();
 }
 firebaseReference = database.getReference("range");
 firebaseReference.child(lightRange.getType()).setValue(lightRange);

 }
 checkInRange();
 }


public void checkInRange(){
    if (lightRange.isRangeSet() && !lightRange.isInRange(lightUI.getPercentLight())) {
 lightExposureValue.setTextColor(Color.parseColor("#FF0000"));

    } else {
 lightExposureValue.setTextColor(Color.parseColor("#000000"));

    }
}
}