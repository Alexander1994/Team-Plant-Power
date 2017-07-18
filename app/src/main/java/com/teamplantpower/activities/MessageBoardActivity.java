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

public class MessageBoardActivity extends AppCompatActivity {
    private EditText enterInput;
    private TextView nameText;
    private Button submit;

    private boolean loggedIn = false;

    private ListView messageListView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference messageDbRef = database.getReference(Message.DBMessageListKey);
    DatabaseReference userDbRef = database.getReference(User.DBUserListKey);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board);

        enterInput = (EditText) findViewById(R.id.EnterName);
        nameText = (TextView) findViewById(R.id.Name);
        submit = (Button) findViewById(R.id.Submit);
        messageListView = (ListView) findViewById(R.id.List);

        userDbRef.orderByKey().equalTo(User.getId(getApplicationContext())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null && dataSnapshot.getValue()!=null) {
                    nameText.setText(dataSnapshot.child(User.getId(getApplicationContext())).getValue(String.class));
                    enterInput.setText("Enter Message");
                    loggedIn = true;
                } else {
                    nameText.setText(" ");
                    enterInput.setText("Enter Name");
                    loggedIn = false;
                }
            }
            public void onCancelled(DatabaseError err) {

            }
        });
    }

    public void setUser(View v) {

        String name = enterInput.getText().toString();
        User u = new User(name);
        userDbRef.child(User.getId(getApplicationContext())).setValue(u.getName());
    }

}
