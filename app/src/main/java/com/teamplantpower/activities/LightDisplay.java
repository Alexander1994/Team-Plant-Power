package com.teamplantpower.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.teamplantpower.team_plant_power.Database;
import com.teamplantpower.team_plant_power.Light;
import com.teamplantpower.team_plant_power.R;
import com.teamplantpower.team_plant_power.Range;

public class LightDisplay extends AppCompatActivity {
    //UI Elements
    TextView lightExposureValue;
    Button refreshLightExposure;
    EditText setMinLight;
    EditText setMaxLight;
    Button setMin_MaxLight;
    TextView message;
    //Datebase and Light Exposure Objects
    Database data;
    Light light_exposure;
    Range lightRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_display);
        //Initialize Database and Light Exposure Objects
        data = new Database();
        light_exposure = new Light(data.getLightData());
        lightRange = new Range("humidity", 0,100);//default values

        //Initialze UI elements
        lightExposureValue = (TextView) findViewById(R.id.lightExposureValue);
        setMinLight = (EditText) findViewById(R.id.setMinLight);
        setMaxLight = (EditText) findViewById(R.id.setMaxLight);
        setMin_MaxLight = (Button) findViewById(R.id.setLight);
        message = (TextView) findViewById(R.id.message);
        //Display Current Light Exposure Values on UI
        lightExposureValue.setText(Double.toString(light_exposure.getPercentLight()));
        setMinLight.setText(Double.toString(lightRange.getMinRange()));
        setMaxLight.setText(Double.toString(lightRange.getMaxRange()));

        //Handle Updates to the Min and Max Light Exposure Values
        setMin_MaxLight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                lightRange.setMinRange(Double.parseDouble(setMinLight.getText().toString()));
                lightRange.setMaxRange(Double.parseDouble(setMaxLight.getText().toString()));
                //If the range provided by the user is not valid reset to default range
                if (!lightRange.validateRange()) {
                    lightRange.setMinRange(0.0);
                    lightRange.setMaxRange(100.0);
                    setMinLight.setText(Double.toString(lightRange.getMinRange()));
                    setMaxLight.setText(Double.toString(lightRange.getMaxRange()));
                }
                //Display a message depending to inform user if light exposure in within the desired range
                if (lightRange.isInRange(light_exposure.getPercentLight())) {
                    message.setText("Light Exposure in Greenhouse OK");
                } else {
                    message.setText("WARNING! Light Exposure in Greenhouse NOT OK!");

                }
            }


        });
    }
}
