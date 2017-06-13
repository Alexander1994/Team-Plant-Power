package com.teamplantpower.team_plant_power;

/**
 * Created by Mishal on 08/06/2017.
 */

public class Light_UI {
    
    double percentLight;
    double minLight;
    double maxLight;
 
    //Constructor to set light and minimum and maximum light range
    public  Light_UI (double light, double min, double max){
        percentLight = light;
        minLight = min;
        maxLight = max;
    }
    //Constructor to set the light. Range is set to default (0-100)
    public  Light_UI (double light){
        percentLight = light;
        minLight = 0;
        maxLight = 100;
    }
    //Gettters
    public double getlight(){
        return percentLight;
    }
    public double getminLight(){
        return minLight;
    }
    public double getmaxLight(){
        return maxLight;
    }
    //Setters
    public void setlight(double light){
        percentLight = light;
    }
    public void setminLight(double min){
        minLight = min;
    }
    public void setmaxLight(double max){
        maxLight = max;
    }
    //Method to validate light value is >= 0 and <= 100
    public boolean validateLight(){
        if(this.getlight() < 0 || this.getlight() > 100)
            return false;
        else
            return true;
    }
    //Method to validate light range values are >=0, <=100 and that the min and max light do not overlap
    public boolean validateLightRange(){
        if(this.getminLight() < 0 || this.getminLight() > 100 || this.getmaxLight() < 0 || this.getmaxLight() > 100)
            return false;
        else if(this.getminLight() > this.getmaxLight())
            return false;
        else
            return true;
    }
    //Method to check if the current light value is within the current range set by user
    public boolean checkLightInRange(){
        if(this.getlight() < this.getminLight() || this.getlight() > this.getmaxLight())
            return false;
        else
            return true;
    }
 }

