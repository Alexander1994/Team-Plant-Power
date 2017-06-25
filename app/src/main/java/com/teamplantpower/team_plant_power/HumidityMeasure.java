package com.teamplantpower.team_plant_power;

/**
 * Class for representing a humidity value and its range
 * @author Brandon/Najla
 */
public class HumidityMeasure {
    /**
     * Instance Variables
     * percentHumidity represents the humidity value in percent, can be read and wrote to using getters and setters
     * minHumidity represents the minimum humidity can be read and wrote to using getters and setters
     * maxHumidity represents the maximum humidity can be read and wrote to using getters and setters
     */
    double percentHumidity;
    double minHumidity;
    double maxHumidity;

    /**
     * Constructor to set humidity and minimum and maximum humidity range
     * @param humid Humidity value in percent
     * @param min minimum range of the humidity value
     * @param max maximum range of the humidity value
     */
    public HumidityMeasure(double humid, double min, double max){
        percentHumidity = humid;
        minHumidity = min;
        maxHumidity = max;
    }

    /**
     * Constructor to set the humidity. Range is set to default (0-100)
     * @param humid Humidity value in percent
     */
    public HumidityMeasure(double humid){
        percentHumidity = humid;
        minHumidity = 0;
        maxHumidity = 100;
    }

    /**
     * Method to get the current Humidity Value
     * @return the humidity value in percent
     */
    public double getHumidity(){
        return percentHumidity;
    }

    /**
     * Method to get the current minimum humidity value range
     * @return the minimum humidity range value in percent
     */
    public double getMinHumidity(){
        return minHumidity;
    }

    /**
     * Method to get the current maximum humidity value range
     * @return the maximum humidity rnage value in percent
     */
    public double getMaxHumidity(){
        return maxHumidity;
    }

    /**
     * Method to set the humidity value
     * @param humid the value of the humidity in percent
     */
    public void setHumidity(double humid){
        percentHumidity = humid;
    }

    /**
     * Method to set the minimum humidity range
     * @param min the minimum humidity range value in percent
     */
    public void setMinHumidity(double min){
        minHumidity = min;
    }

    /**
     * Method to set the maximum humidity range
     * @param max the maximum humidity range value in percent
     */
    public void setMaxHumidity(double max){
        maxHumidity = max;
    }

    /**
     * Method to validate humidity value is >= 0 and <= 100
     * @return boolean if humidity value is valid
     */
    public boolean validateHumidity(){
        if(this.getHumidity() < 0 || this.getHumidity() > 100)
            return false;
        else
            return true;
    }

    /**
     * Method to validate humidity range values are >=0, <=100 and that the min and max humidity do not overlap
     * @return boolean if the minimum and maximum humidity range values are valid
     */
    public boolean validateHumidityRange(){
        if(this.getMinHumidity() < 0 || this.getMinHumidity() > 100 || this.getMaxHumidity() < 0 || this.getMaxHumidity() > 100)
            return false;
        else if(this.getMinHumidity() > this.getMaxHumidity())
            return false;
        else
            return true;
    }

    /**
     * Method to check if the current humidity value is within the current range set by user
     * @return boolean if the current humidity is within the current range set
     */
    public boolean checkHumidityInRange(){
        if(this.getHumidity() < this.getMinHumidity() || this.getHumidity() > this.getMaxHumidity())
            return false;
        else
            return true;
    }
}
