package com.teamplantpower.team_plant_power;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HumidityDisplay extends AppCompatActivity {
    //UI Elements
    TextView humidityExposureValue;
    Button refreshHumidityExposure;
    EditText setMinHumidity;
    EditText setMaxHumidity;
    Button setMin_MaxHumidity;
    TextView message;
    //Datebase and Humidity Exposure Objects
    Database data;
    HumidityMeasure humidity_exposure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity_display);
        //Initialize Database and Humidity Exposure Objects
        data = new Database();
        humidity_exposure = new HumidityMeasure(data.getHumidityData());
        //Initialze UI elements
        humidityExposureValue = (TextView) findViewById(R.id.humidityExposureValue);
        setMinHumidity = (EditText) findViewById(R.id.setMinHumidity);
        setMaxHumidity = (EditText) findViewById(R.id.setMaxHumidity);
        setMin_MaxHumidity = (Button) findViewById(R.id.setMin_MaxHumidity);
        message = (TextView) findViewById(R.id.message);
        //Display Current Humidity Exposure Values on UI
        humidityExposureValue.setText(Double.toString(humidity_exposure.getHumidity()));
        setMinHumidity.setText(Double.toString(humidity_exposure.getMinHumidity()));
        setMaxHumidity.setText(Double.toString(humidity_exposure.getMaxHumidity()));

        //Handle Updates to the Min and Max Humidity Exposure Values
        setMin_MaxHumidity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                humidity_exposure.setMinHumidity(Double.parseDouble(setMinHumidity.getText().toString()));
                humidity_exposure.setMaxHumidity(Double.parseDouble(setMaxHumidity.getText().toString()));
                //If the range provided by the user is not valid reset to default range
                if (!humidity_exposure.validateHumidityRange()) {
                    humidity_exposure.setMinHumidity(0.0);
                    humidity_exposure.setMaxHumidity(100.0);
                    setMinHumidity.setText(Double.toString(humidity_exposure.getMinHumidity()));
                    setMaxHumidity.setText(Double.toString(humidity_exposure.getMaxHumidity()));
                }
                //Display a message depending to inform user if humidity exposure in within the desired range
                if (humidity_exposure.checkHumidityInRange()) {
                    message.setText("Humidity Exposure in Greenhouse OK");
                } else {
                    message.setText("WARNING! Humidity Exposure in Greenhouse NOT OK!");

                }
            }


        });
    }
}










