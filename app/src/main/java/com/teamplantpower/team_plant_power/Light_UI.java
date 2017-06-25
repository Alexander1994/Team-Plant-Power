package com.teamplantpower.team_plant_power;

/**
 * Class for representing a light exposure value and its range
 * @author Alexander/Mishal
 */

public class Light_UI {

    /**
     * Instance Variables
     * percentLight represents the light exposure value in percent, can be read and wrote to using getters and setters
     * minLight represents the minimum light exposure can be read and wrote to using getters and setters
     * maxLight represents the maximum light exposure can be read and wrote to using getters and setters
     */
    double percentLight;
    double minLight;
    double maxLight;

    /**
     * Constructor to set light exposure and minimum and maximum light exposure range
     * @param light light exposure value in percent
     * @param min minimum range of light exposure in percent
     * @param max maximum range of light exposure in percent
     */
    public  Light_UI (double light, double min, double max){
        percentLight = light;
        minLight = min;
        maxLight = max;
    }

    /**
     * Constructor to set the light. Range is set to default (0-100)
     * @param light light exposure value in percent
     */
    public  Light_UI (double light){
        percentLight = light;
        minLight = 0;
        maxLight = 100;
    }

    /**
     * Method to get the current light exposure value in percent
     * @return light exposure value in percent
     */
    public double getlight(){
        return percentLight;
    }

    /**
     * Method to get the minimum value of the light exposure range in percent
     * @return minimum light exposure range in percent
     */
    public double getminLight(){
        return minLight;
    }

    /**
     * Method to get the maximum value of the light exposure range in percent
     * @return maximum light exposure range in percent
     */
    public double getmaxLight(){
        return maxLight;
    }

    /**
     *  Method to set the light exposure value
     * @param light light exposure value in percent
     */
    public void setlight(double light){
        percentLight = light;
    }

    /**
     * Method to set minimum light exposure range value
     * @param min minimum light exposure range in percent
     */
    public void setminLight(double min){
        minLight = min;
    }

    /**
     * Method to set maximum light exposure range value
     * @param max maximum light exposure range in percent
     */
    public void setmaxLight(double max){
        maxLight = max;
    }

    /**
     * Method to validate light value is >= 0 and <= 100
     * @return boolean if light exposure value is valid
     */
    public boolean validateLight(){
        if(this.getlight() < 0 || this.getlight() > 100)
            return false;
        else
            return true;
    }

    /**
     * Method to validate light range values are >=0, <=100 and that the min and max light do not overlap
     * @return boolean if light exposure range is valid
     */
    public boolean validateLightRange(){
        if(this.getminLight() < 0 || this.getminLight() > 100 || this.getmaxLight() < 0 || this.getmaxLight() > 100)
            return false;
        else if(this.getminLight() > this.getmaxLight())
            return false;
        else
            return true;
    }

    /**
     * Method to check if the current light value is within the current range set by user
     * @return boolean if current light exposure level is within the current range set
     */
    public boolean checkLightInRange(){
        if(this.getlight() < this.getminLight() || this.getlight() > this.getmaxLight())
            return false;
        else
            return true;
    }
 }

