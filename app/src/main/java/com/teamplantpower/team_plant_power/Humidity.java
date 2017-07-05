package com.teamplantpower.team_plant_power;

/**
 * Created by Brandon on 2017-06-07.
 */

public class Humidity {
    private String hID;
    private double percentHumidity;


    /**
     * The constructor
     * @param id   A datestamp string, the primary key.
     * @param h A humidity to set
     */
    public Humidity(String id, double h){
        hID = id;
        percentHumidity = h;
    }

    /**
     * The default constructor
     * @param h A humidity to set
     */
    public Humidity(double h){
        String hID = "";
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
    public boolean validateHumidity(){
        if(this.percentHumidity < 0 || this.percentHumidity > 100)
            return false;
        else
            return true;
    }

}