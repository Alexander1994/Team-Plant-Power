package com.teamplantpower.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamplantpower.team_plant_power.Humidity;
import com.teamplantpower.team_plant_power.Light;
import com.teamplantpower.team_plant_power.R;
import com.teamplantpower.team_plant_power.Range;
import com.teamplantpower.team_plant_power.Temperature;

import java.util.Timer;
import java.util.TimerTask;

import static com.teamplantpower.team_plant_power.R.drawable.notification_icon;
import static com.teamplantpower.team_plant_power.R.drawable.sun;
import static com.teamplantpower.team_plant_power.R.drawable.thermometer;
import static com.teamplantpower.team_plant_power.R.drawable.water;
import static com.teamplantpower.team_plant_power.R.mipmap.ic_launcher;

/**
 * Created by Mishal on 18/07/2017.
 */

public class MyServiceNotification extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference firebaseReference;

    int delay = 0; // delay for 0 sec.
    int period = 10000; // repeat every 10 sec.
    Timer timer = new Timer();

    //temp variables
    private Temperature temperatureUI = new Temperature();
    Range temperatureRange = new Range("temperature", Double.NaN, Double.NaN);

    //light variables
    private Light lightUI = new Light();
    Range lightRange = new Range ("light", 0,100);

    //humidity variables
    private Humidity humidityUI = new Humidity();
    Range humidityRange = new Range ("humidity", 0,100);

    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                TempNotification();
                LightNotification();
                HumidityNotification();
            }
        }, delay, period);

        return super.onStartCommand(intent, flags, startId);

    }




    //notification LIGHT
    public void LightNotification() {

        //get range
        firebaseReference = database.getReference("range/light");
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                lightRange = dataSnapshot.getValue(Range.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //get light
        firebaseReference = database.getReference("currentLight");
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //remove any characters like letters or symbols
                double lightx = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
                lightUI.setPercentLight(lightx);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (lightRange.isRangeSet() && !lightRange.isInRange(lightUI.getPercentLight())) {
            android.support.v4.app.NotificationCompat.Builder mBuilder2 = new NotificationCompat.Builder(this)
                    .setSmallIcon(sun)
                    .setContentTitle("warning")
                    .setContentText("Light out of Range  " + lightUI.getPercentLight());
            // Sets an ID for the notification
            int mNotificationId = 002;
            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, mBuilder2.build());

        } else {}
    }

    //notification HUMIDITY
    public void HumidityNotification(){

        //get range
        firebaseReference = database.getReference("range/humidity");
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                humidityRange = dataSnapshot.getValue(Range.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //get humidity
        firebaseReference = database.getReference("currentHumidity");
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //remove any characters like letters or symbols
                double humidityx = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
                humidityUI.setPercentHumidity(humidityx);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (humidityRange.isRangeSet() && !humidityRange.isInRange(humidityUI.getPercentHumidity())) {
            android.support.v4.app.NotificationCompat.Builder mBuilder3 = new NotificationCompat.Builder(this)
                    .setSmallIcon(water)
                    .setContentTitle("warning")
                    .setContentText("Humidity out of Range  " + humidityUI.getPercentHumidity());
            // Sets an ID for the notification
            int mNotificationId = 003;
            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, mBuilder3.build());


        } else {}

    }

    //notification TEMPERATURE
    public void TempNotification() {

        //get range
        firebaseReference = database.getReference("range/temperature");
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                temperatureRange = dataSnapshot.getValue(Range.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //get temperature
        firebaseReference = database.getReference("currentTemperature");
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //remove any characters like letters or symbols
                double temp = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
                temperatureUI.setCelciusValue(temp);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        if (temperatureRange.isRangeSet() && !temperatureRange.isInRange(temperatureUI.getCelciusValue())) {
    ;
            android.support.v4.app.NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(thermometer)
                    .setContentTitle("warning")
                    .setContentText("Temperature out of Range  " + temperatureUI.getCelciusValue());
            // Sets an ID for the notification
            int mNotificationId = 001;
            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, mBuilder.build());

        } else {

        }

    }
}
