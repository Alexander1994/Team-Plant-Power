package com.teamplantpower.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamplantpower.team_plant_power.Message;
import com.teamplantpower.team_plant_power.R;
import com.teamplantpower.team_plant_power.User;
import com.firebase.ui.database.FirebaseListAdapter;

import java.util.Date;

import static android.R.attr.data;

public class MessageBoardActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference messageDbRef = database.getReference(Message.DBMessageListKey);
    DatabaseReference userDbRef = database.getReference(User.DBUserListKey);

    private EditText enterInput;
    private TextView nameText;
    private Button submit;

    private boolean loggedIn = false;
    private User u;

    private ListView messageListView;
    private FirebaseListAdapter<Message> firebaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board);

        enterInput = (EditText) findViewById(R.id.EnterName);
        nameText = (TextView) findViewById(R.id.Name);
        submit = (Button) findViewById(R.id.Submit);

        // Load User if signed up
        userDbRef.orderByKey().equalTo(User.getId(getApplicationContext())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null && dataSnapshot.getValue()!=null) {
                    String name = dataSnapshot.child(User.getId(getApplicationContext())).getValue(String.class);
                    u = new User(name);
                    nameText.setText(u.getName());
                    enterInput.setText("Enter Message");
                    loggedIn = true;

                } else {
                    u = null;
                    nameText.setText(" ");
                    enterInput.setText("Enter Name");
                    loggedIn = false;
                }
            }
            public void onCancelled(DatabaseError err) {

            }
        });

        //Populate message list
        messageListView = (ListView) findViewById(R.id.List);

        //Set up the List View
        firebaseAdapter = new FirebaseListAdapter<Message>(this, Message.class,
                android.R.layout.simple_list_item_1, messageDbRef) {
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.getName()+ ": " +model.getMessage());
            }
        };
        messageListView.setAdapter(firebaseAdapter);
    }

    public void setInput(View v) {
        String input = enterInput.getText().toString();
        Date date = new Date();
        if (loggedIn) {
            Message m = new Message(u.getName(), input);
            messageDbRef.child(""+date.getTime()).setValue(m.toMap());

        } else { // add user to DB
            userDbRef.child(User.getId(getApplicationContext())).setValue(input);
        }

    }

}
