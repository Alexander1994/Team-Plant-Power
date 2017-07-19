package com.teamplantpower.team_plant_power;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase database. This is converted to a JSON format
 */

public class Message implements Serializable {

    public String name;
    public String message;
    public static final String DBMessageListKey = "Messages";


    /**
     * Default constructor required for calls to DataSnapshot.getValue
     */
    public Message() {}

    /**
     * Constructors for instance of a message to be submitted to firebase.
     * @param name
     * @param message
     */
    public Message(String name, String message){
        this.name = name;
        this.message = message;
    }

    /**
     * converts object to hashmap
     * @return Hashmap of Message object
     */

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("message", message);
        return result;
    }

    /**
     * getter for message
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * getter for name
     * @return
     */
    public String getName() {
        return name;
    }
}
