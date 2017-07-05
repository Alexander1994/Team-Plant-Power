package com.teamplantpower.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.teamplantpower.team_plant_power.Database;
import com.teamplantpower.team_plant_power.R;
import com.teamplantpower.team_plant_power.Range;
import com.teamplantpower.team_plant_power.Temperature;

public class TemperatureDisplay extends AppCompatActivity {

    private Temperature temperatureUI;
    private Range temperatureRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_display);
        temperatureUI = new Temperature();
        temperatureRange = new Range(0,100);


    }

    public void refreshValues(View v) {
        Database db = new Database();
        TextView celciusValue = (TextView) findViewById(R.id.celciusValue);
        TextView farenheitValue = (TextView) findViewById(R.id.fahrenheitValue);
        temperatureUI.setCelciusValue(db.getTemperatureData());
        celciusValue.setText(Double.toString(temperatureUI.getCelciusValue()));
        farenheitValue.setText(Double.toString(temperatureUI.getFarenheitValue()));

        if (temperatureRange.isRangeSet() && !temperatureRange.isInRange(temperatureUI.getCelciusValue())) {
            farenheitValue.setTextColor(Color.parseColor("#FF0000"));
            celciusValue.setTextColor(Color.parseColor("#FF0000"));

        } else {
            farenheitValue.setTextColor(Color.parseColor("#000000"));
            celciusValue.setTextColor(Color.parseColor("#000000"));

        }
    }

    public void setRange(View v) {
        EditText minimumValue = (EditText) findViewById(R.id.setMinTemp);
        EditText maximumValue = (EditText) findViewById(R.id.setMaxTemp);

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
        }

    }
}
