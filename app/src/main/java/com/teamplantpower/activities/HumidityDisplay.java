package com.teamplantpower.activities;

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
    TextView humidityExposureValue;
    Button refreshHumidityExposure;
    EditText setMinHumidity;
    EditText setMaxHumidity;
    Button setMin_MaxHumidity;
    TextView message;
    //Datebase and Humidity Exposure Objects
    Database data;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference("currentHumidity");
    DatabaseReference ref2;
    Humidity humidity_exposure;
    Range humidityRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity_display);
        //Initialize Database and Humidity Exposure Objects
        data = new Database();
        humidity_exposure = new Humidity(data.getHumidityData());
        //Initialze UI elements
        humidityExposureValue = (TextView) findViewById(R.id.humidityExposureValue);
        setMinHumidity = (EditText) findViewById(R.id.setMinHumidity);
        setMaxHumidity = (EditText) findViewById(R.id.setMaxHumidity);
        setMin_MaxHumidity = (Button) findViewById(R.id.setHumidity);
        message = (TextView) findViewById(R.id.message);
        //Display Current Humidity Exposure Values on UI
        humidityRange = new Range("humidity", 0,100);
        humidityExposureValue.setText(Double.toString(humidity_exposure.getPercentHumidity()));
        setMinHumidity.setText(Double.toString(humidityRange.getMinRange()));
        setMaxHumidity.setText(Double.toString(humidityRange.getMaxRange()));

        //Retrieve updated humidity values from database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String humidity = dataSnapshot.getValue(String.class);
                double value = Double.parseDouble(humidity.replaceAll("[^\\d.]", ""));
                humidity_exposure.setPercentHumidity(value);
                humidityExposureValue.setText(humidity);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //Handle Updates to the Min and Max Humidity Exposure Values
        setMin_MaxHumidity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                humidityRange.setMinRange(Double.parseDouble(setMinHumidity.getText().toString()));
                humidityRange.setMaxRange(Double.parseDouble(setMaxHumidity.getText().toString()));
                //If the range provided by the user is not valid reset to default range
                if (!humidityRange.validateRange()) {
                    humidityRange.setMinRange(0.0);
                    humidityRange.setMaxRange(100.0);
                    setMinHumidity.setText(Double.toString(humidityRange.getMinRange()));
                    setMaxHumidity.setText(Double.toString(humidityRange.getMaxRange()));
                }
                ref2 = db.getReference("range");

                ref2.child(humidityRange.getType()).setValue(humidityRange);
                //Display a message depending to inform user if humidity exposure in within the desired range
                if (humidityRange.isInRange(humidity_exposure.getPercentHumidity())) {
                    message.setText("Humidity Exposure in Greenhouse OK");
                } else {
                    message.setText("WARNING! Humidity Exposure in Greenhouse NOT OK!");

                }
            }


        });
    }
}










