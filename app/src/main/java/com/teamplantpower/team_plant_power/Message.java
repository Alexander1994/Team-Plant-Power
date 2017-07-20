package com.teamplantpower.team_plant_power;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
     * @return the text in the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * getter for name
     * @return The users name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * retrieves the full display text for a message
     * @param dateStr
     * @return full display text for a message
     */
    public String getFullDisplayMessage(String dateStr) {
        return msgKeyToDisplayStr(dateStr)+ ", "+name+ ": " +message;
    }

    /**
     * creates and returns a message key to be used by the DB
     * @return key for the message in the db
     */
    public static String createMsgKey() {
        Date date = new Date();
        return ""+date.getTime();
    }

    /**
     * converts date.getTime() as a string to the display string for the display
     * @param dateStr
     * @return display string for the UI
     */
    public static String msgKeyToDisplayStr(String dateStr) {
        Date d = new Date(Long.valueOf(dateStr));
        return  (new SimpleDateFormat("MMM dd HH:mm").format(d));
    }
}
