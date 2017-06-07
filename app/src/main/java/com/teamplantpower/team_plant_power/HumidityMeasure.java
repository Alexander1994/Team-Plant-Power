package com.teamplantpower.team_plant_power;

/**
 * Created by Brandon on 2017-06-07.
 */

public class HumidityMeasure {
    double percentHumidity;
    double minHumidity;
    double maxHumidity;

    public HumidityMeasure(double humid, double min, double max){
        percentHumidity = humid;
        minHumidity = min;
        maxHumidity = max;
    }
    public double getHumidity(){
        return percentHumidity;
    }
    public double getMinHumidity(){
        return minHumidity;
    }
    public double getMaxHumidity(){
        return maxHumidity;
    }
    public void setHumidity(double humid){
        percentHumidity = humid;
    }
    public void setMinHumidity(double min){
        minHumidity = min;
    }
    public void setMaxHumidity(double max){
        maxHumidity = max;
    }
    public static boolean validateHumidity(HumidityMeasure h){
        if(h.getHumidity() < 0 || h.getHumidity() > 100)
            return false;
        else
            return true;
    }
    public static boolean validateHumidityRange(HumidityMeasure h){
        if(h.getMinHumidity() < 0 || h.getMinHumidity() > 100 || h.getMaxHumidity() < 0 || h.getMaxHumidity() > 100)
            return false;
        else if(h.getMinHumidity() > h.getMaxHumidity())
            return false;
        else
            return true;
    }
    public static boolean checkHumidityInRange(HumidityMeasure h){
        if(h.getHumidity() < h.getMinHumidity() || h.getHumidity() > h.getMaxHumidity())
            return false;
        else
            return true;
    }
}
