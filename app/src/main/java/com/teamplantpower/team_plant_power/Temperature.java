package com.teamplantpower.team_plant_power;


public class Temperature {
    private String tID;
    private double celciusValue;

    /**
     * Default constructor
     */
    public Temperature() {
        tID = "";
        celciusValue = Double.NaN;
    }

    /**
     * Constructor that takes an id and the temperature value in celcius
     * @param tID   A string, the timestamp id.
     * @param celciusValue  The celcius value.
     */
    public Temperature(String tID, double celciusValue) {
        this.tID = tID;
        this.celciusValue = celciusValue;
    }


    //Setters******************************************************************
    /**
     * Set the celcius value
     * @param val the celcius value to set
     */
    public void setCelciusValue(double val){
        celciusValue = val;
    }

    /**
     * Set the timestamp id value
     * @param tID the id value to set
     */
    public void settID(String tID) {this.tID = tID;}



    //Getters******************************************************************
    /**
     * Converts the celcius value to fahrenheit
     * @return  A fahrenheit value.
     */
    public double getFarenheitValue() {
        return celciusValue * 1.8 + 32;
    }

    /**
     * Get the celcius value
     * @return  The celcius value
     */
    public double getCelciusValue() {
        return celciusValue;
    }

    /**
     * Get the id
     * @return  The temperature timestamp id
     */
    public String gettID() {return tID;}


}
