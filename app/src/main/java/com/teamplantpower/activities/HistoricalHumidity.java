package com.teamplantpower.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamplantpower.team_plant_power.Humidity;
import com.teamplantpower.team_plant_power.R;

import java.util.Date;

public class HistoricalHumidity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference firebaseReference = database.getReference("historicalHumidity");
    private ListView historicalListView;
    private FirebaseListAdapter<Humidity> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_humidity);



        //Populate message list
        historicalListView = (ListView) findViewById(R.id.humidityList);

        //Set up the List View
        firebaseAdapter = new FirebaseListAdapter<Humidity>(this, Humidity.class,
                android.R.layout.two_line_list_item, firebaseReference) {
            @Override
            protected void populateView(View v, Humidity model, int position) {
                TextView light = (TextView)v.findViewById(android.R.id.text1);
                light.setText(model.getPercentHumidity()+ "");

                Date date = new Date(Long.parseLong(model.gethID()));
                TextView dateField = (TextView)v.findViewById(android.R.id.text2);
                dateField.setText(date.toString());
            }
        };
        historicalListView.setAdapter(firebaseAdapter);
    }

}

