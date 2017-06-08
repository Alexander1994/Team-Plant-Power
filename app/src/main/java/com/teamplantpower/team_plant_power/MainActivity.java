package com.teamplantpower.team_plant_power;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };
    /**
     * Called when the user taps the Humidity button
     * @param view The current view.
     */
    public void loadHumidity(View view) {
        Intent intent = new Intent(this, HumidityDisplay.class);
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

        //mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
