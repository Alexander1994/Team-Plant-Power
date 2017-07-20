package com.teamplantpower.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.teamplantpower.team_plant_power.R;

public class MainActivity extends AppCompatActivity {
    /**
     * Called when the user taps the Humidity button
     * @param view The current view.
     */
    public void loadHumidity(View view) {
        Intent intent = new Intent(this, HumidityDisplay.class);
        startActivity(intent);
    }
    /**
     * Called when the user taps the Message Board button button
     * @param view The current view.
     */
    public void loadMessageBoard(View view) {
        Intent intent = new Intent(this, MessageBoardActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user taps the Light button
     * @param view The current view.
     */
    public void loadLight(View view) {
        Intent intent = new Intent(this, LightDisplay.class);
        startActivity(intent);
    }

    /**
     * Called when the user taps the Temperature button
     * @param view The current view.
     */
    public void loadTemperature(View view) {
        Intent intent = new Intent(this, TemperatureDisplay.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
