package com.teamplantpower.team_plant_power;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brandon on 2017-06-07.
 */

public class Humidity implements Serializable {
    public String hID;
    public double percentHumidity;

    /**
     * The default constructor
     */
    public Humidity(){
        String hID = "";
        percentHumidity = Double.NaN;
    }

    /**
     * One argument costructor
     * @param h A humidity to set
     */
    public Humidity(double h){
        String hID = "";
        percentHumidity = h;
    }

    /**
     * The constructor
     * @param id   A datestamp string, the primary key.
     * @param h A humidity to set
     */
    public Humidity(String id, double h){
        hID = id;
        percentHumidity = h;
    }




    //Getters******************************************************************
    /**
     * Get the hID
     * @return  The hID
     */
    public String gethID() {
        return hID;
    }

    /**
     * Get the percent Humidity
     * @return  The percent Humidity
     */
    public double getPercentHumidity() {
        return percentHumidity;
    }


    //Setters******************************************************************
    /**
     * Set
     * @param hID  The hID to set
     */
    @Exclude
    public void sethID(String hID) {
        this.hID = hID;
    }



    /**
     * Set humidity value
     * @param percentHumidity  The percentHumidity to set
     */
    @Exclude
    public void setPercentHumidity(double percentHumidity) {
        this.percentHumidity = percentHumidity;
    }



    //Validation******************************************************************

    /**
     * Check whether humidity is a valid value.
     * @return  A boolean, true if it's between 0 and 100, false otherwise.
     */
    @Exclude
    public boolean validateHumidity(){
        if(this.percentHumidity < 0 || this.percentHumidity > 100)
            return false;
        else
            return true;
    }

    /**
     * Convert object to hashmap
     * @return A hashmap
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("hID", hID);
        result.put("percentHumidity", percentHumidity);
        return result;
    }

}
