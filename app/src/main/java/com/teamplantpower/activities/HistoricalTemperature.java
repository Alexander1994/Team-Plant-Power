package com.teamplantpower.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamplantpower.team_plant_power.R;
import com.teamplantpower.team_plant_power.Temperature;

import java.util.Date;

public class HistoricalTemperature extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference firebaseReference = database.getReference("historicalTemperature");
    private ListView historicalListView;
    private FirebaseListAdapter<Temperature> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_temperature);



    //Populate message list
        historicalListView = (ListView) findViewById(R.id.temperatureList);

        //Set up the List View
        firebaseAdapter = new FirebaseListAdapter<Temperature>(this, Temperature.class,
                android.R.layout.two_line_list_item, firebaseReference) {
            @Override
            protected void populateView(View v, Temperature model, int position) {
                TextView temperature = (TextView)v.findViewById(android.R.id.text1);
                temperature.setText(model.getCelciusValue()+ "");

                Date date = new Date(Long.parseLong(model.gettID()));
                TextView dateField = (TextView)v.findViewById(android.R.id.text2);
                dateField.setText(date.toString());
            }
        };
        historicalListView.setAdapter(firebaseAdapter);
    }

}
