package com.teamplantpower.team_plant_power;

/**
 * Created by Brandon on 2017-06-07.
 */

public class HumidityMeasure {
    double percentHumidity;
    double minHumidity;
    double maxHumidity;

    //Constructor to set humidity and minimum and maximum humidity range
    public HumidityMeasure(double humid, double min, double max){
        percentHumidity = humid;
        minHumidity = min;
        maxHumidity = max;
    }
    //Constructor to set the humidity. Range is set to default (0-100)
    public HumidityMeasure(double humid){
        percentHumidity = humid;
        minHumidity = 0;
        maxHumidity = 100;
    }
    //Gettters
    public double getHumidity(){
        return percentHumidity;
    }
    public double getMinHumidity(){
        return minHumidity;
    }
    public double getMaxHumidity(){
        return maxHumidity;
    }
    //Setters
    public void setHumidity(double humid){
        percentHumidity = humid;
    }
    public void setMinHumidity(double min){
        minHumidity = min;
    }
    public void setMaxHumidity(double max){
        maxHumidity = max;
    }
    //Method to validate humidity value is >= 0 and <= 100
    public boolean validateHumidity(){
        if(this.getHumidity() < 0 || this.getHumidity() > 100)
            return false;
        else
            return true;
    }
    //Method to validate humidity range values are >=0, <=100 and that the min and max humidity do not overlap
    public boolean validateHumidityRange(){
        if(this.getMinHumidity() < 0 || this.getMinHumidity() > 100 || this.getMaxHumidity() < 0 || this.getMaxHumidity() > 100)
            return false;
        else if(this.getMinHumidity() > this.getMaxHumidity())
            return false;
        else
            return true;
    }
    //Method to check if the current humidity value is within the current range set by user
    public boolean checkHumidityInRange(){
        if(this.getHumidity() < this.getMinHumidity() || this.getHumidity() > this.getMaxHumidity())
            return false;
        else
            return true;
    }
}
