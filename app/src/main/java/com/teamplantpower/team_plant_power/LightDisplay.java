package com.teamplantpower.team_plant_power;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    Light_UI light_exposure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_display);
        //Initialize Database and Light Exposure Objects
        data = new Database();
        light_exposure = new Light_UI(data.getLightData());
        //Initialze UI elements
        lightExposureValue = (TextView) findViewById(R.id.lightExposureValue);
        setMinLight = (EditText) findViewById(R.id.setMinLight);
        setMaxLight = (EditText) findViewById(R.id.setMaxLight);
        setMin_MaxLight = (Button) findViewById(R.id.setMin_MaxLight);
        message = (TextView) findViewById(R.id.message);
        //Display Current Light Exposure Values on UI
        lightExposureValue.setText(Double.toString(light_exposure.getlight()));
        setMinLight.setText(Double.toString(light_exposure.getminLight()));
        setMaxLight.setText(Double.toString(light_exposure.getmaxLight()));

        //Handle Updates to the Min and Max Light Exposure Values
        setMin_MaxLight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                light_exposure.setminLight(Double.parseDouble(setMinLight.getText().toString()));
                light_exposure.setmaxLight(Double.parseDouble(setMaxLight.getText().toString()));
                //If the range provided by the user is not valid reset to default range
                if (!light_exposure.validateLightRange()) {
                    light_exposure.setminLight(0.0);
                    light_exposure.setmaxLight(100.0);
                    setMinLight.setText(Double.toString(light_exposure.getminLight()));
                    setMaxLight.setText(Double.toString(light_exposure.getmaxLight()));
                }
                //Display a message depending to inform user if light exposure in within the desired range
                if (light_exposure.checkLightInRange()) {
                    message.setText("Light Exposure in Greenhouse OK");
                } else {
                    message.setText("WARNING! Light Exposure in Greenhouse NOT OK!");

                }
            }


        });
    }
}
