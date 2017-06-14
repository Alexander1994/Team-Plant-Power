package com.teamplantpower.team_plant_power;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TemperatureDisplay extends AppCompatActivity {

    private Temperature temperatureUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_display);
        temperatureUI = new Temperature();

    }

    public void refreshValues(View v) {
        Database db = new Database();
        TextView celciusValue = (TextView) findViewById(R.id.celciusValue);
        TextView farenheitValue = (TextView) findViewById(R.id.textView2);
        temperatureUI.setCelciusValue(db.getTemperatureData());
        celciusValue.setText(Double.toString(temperatureUI.getCelciusValue()));
        farenheitValue.setText(Double.toString(temperatureUI.getFarenheitValue()));

        if (temperatureUI.isRangeSet() && !temperatureUI.isInRange()) {
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

            boolean isRangeSet = temperatureUI.setMaxRange(Double.parseDouble(maxString)) &&
                    temperatureUI.setMinRange(Double.parseDouble(minString));
            if (!isRangeSet)  {
                minimumValue.setText("Min");
                maximumValue.setText("Max");
                temperatureUI.resetRange();
            }
        }

    }
}
