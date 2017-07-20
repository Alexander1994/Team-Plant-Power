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
                android.R.layout.simple_list_item_1, firebaseReference) {
            @Override
            protected void populateView(View v, Temperature model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.getCelciusValue()+ "");
            }
        };
        historicalListView.setAdapter(firebaseAdapter);
    }

}
