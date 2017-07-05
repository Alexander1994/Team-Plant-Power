package com.teamplantpower.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.teamplantpower.team_plant_power.Database;
import com.teamplantpower.team_plant_power.Humidity;
import com.teamplantpower.team_plant_power.R;
import com.teamplantpower.team_plant_power.Range;


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
    Humidity humidity_exposure;
    Range humidityRange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity_display);
        //Initialize Database and Humidity Exposure Objects
        data = new Database();
        humidity_exposure = new Humidity(data.getHumidityData());
        humidityRange = new Range("humidity", 0,100);

        //Initialze UI elements
        humidityExposureValue = (TextView) findViewById(R.id.humidityExposureValue);
        setMinHumidity = (EditText) findViewById(R.id.setMinHumidity);
        setMaxHumidity = (EditText) findViewById(R.id.setMaxHumidity);
        setMin_MaxHumidity = (Button) findViewById(R.id.setHumidity);
        message = (TextView) findViewById(R.id.message);
        //Display Current Humidity Exposure Values on UI
        humidityExposureValue.setText(Double.toString(humidity_exposure.getPercentHumidity()));
        setMinHumidity.setText(Double.toString(humidityRange.getMinRange()));
        setMaxHumidity.setText(Double.toString(humidityRange.getMaxRange()));

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










