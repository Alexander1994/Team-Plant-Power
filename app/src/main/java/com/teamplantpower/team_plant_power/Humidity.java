package com.teamplantpower.team_plant_power;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brandon on 2017-06-07.
 */

public class Humidity implements Serializable {
    private String hID;
    private double percentHumidity;


    /**
     * The constructor
     * @param hID   A datestamp string, the primary key.
     * @param percentHumidity A humidity to set
     */
    public Humidity(String hID, double percentHumidity) {
        this.hID = hID;
        this.percentHumidity = percentHumidity;
    }



    /**
     * The default constructor
     * @param h A humidity to set
     */
    public Humidity(double h){
        String hID = "";
        percentHumidity = h;
    }
    public Humidity() {
        hID = "";
        percentHumidity = Double.NaN;
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
     * Set
     * @param hID  The hID to set
     */

    //Setters******************************************************************
    public void sethID(String hID) {
        this.hID = hID;
    }
    /**
     * Get the percent Humidity
     * @return  The percent Humidity
     */
    public double getPercentHumidity() {
        return percentHumidity;
    }

    /**
     * Set humidity value
     * @param percentHumidity  The percentHumidity to set
     */
    public void setPercentHumidity(double percentHumidity) {
        this.percentHumidity = percentHumidity;
    }

    /**
     * Method to validate humidity value is >= 0 and <= 100
     * @return a boolean, true if in range, false otherwise
     */


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
