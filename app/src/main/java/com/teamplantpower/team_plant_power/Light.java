package com.teamplantpower.team_plant_power;

/**
 * Created by Mishal on 08/06/2017.
 */

public class Light {
    String lID;
    double percentLight;

    /**
     * Constructor that sets the light and the id.
     * @param lID The light ID to set, a timestamp.
     * @param percentLight The light percent
     */
    public Light(String lID, double percentLight) {
        this.lID = lID;
        this.percentLight = percentLight;
    }

    /**
     * Constructor to set light
     * @param light the light to set
     */
    public Light(double light){
        lID = "";
        percentLight = light;

    }

    //Getters******************************************************************
    /**
     * Get the id
     * @return  The light timestamp id
     */
    public String getlID() {return lID;}

    /**
     * Get the light value
     * @return  The percent value of light
     */
    public double getPercentLight(){
        return percentLight;
    }

    //Setters******************************************************************
    /**
     * Set the light percentage
     * @param light the light value to set
     */
    public void setPercentLight(double light){
        percentLight = light;
    }

    /**
     * Set the timestamp id value
     * @param lID the id value to set
     */
    public void setlID(String lID) {this.lID = lID;}


    //Validation******************************************************************
    public boolean validateLight(){
        if(this.percentLight < 0 || this.percentLight > 100)
            return false;
        else
            return true;
    }

 }

