package com.teamplantpower.team_plant_power;

/**
 * Class for representing a temperature value and its range
 * @author Alexander/Mishal
 *
 */
public class Temperature {
    /**
     * Instance Variables
     * celciusValue represents the temperature in Celsius can be read and wrote to using getters and setters
     * minRange represents the minimum temperature can be read and wrote to using getters and setters
     * maxRange represents the maximum temperature can be read and wrote to using getters and setters
     */
    double minRange, maxRange, celciusValue;

    /**
     * Temperature Constructor
     */
    public Temperature() {
        minRange = Double.NaN;
        maxRange = Double.NaN;
        celciusValue = Double.NaN;
    }

    /**
     * Method to set the minimum range of the temperature value
     * @param min the value to set the minimum temperature range to
     * @return a boolean to indicate if the minimum temperature range was successfully set
     */
    public boolean setMinRange(double min) {
        if (min<= maxRange || Double.isNaN(maxRange)) {
            minRange = min;
            return true;
        }
        return false;
    }

    /**
     * Method to set the maximum range of the temperature value
     * @param max the value to set the maximum temperature range to
     * @return a boolean to indicate if the maximum temperature range was successfully set
     */
    public boolean setMaxRange(double max) {
        if (max >= minRange || Double.isNaN(minRange)) {
            maxRange = max;
            return true;
        }
        return false;
    }

    /**
     * Method to reset the minimum and maximum temperature ranges
     */
    public void resetRange() {
        minRange = Double.NaN;
        maxRange = Double.NaN;
    }

    /**
     * Method to set the temperature value in celsius
     * @param val the temperature value to set to
     */
    public void setCelciusValue(double val){
        celciusValue = val;
    }

    /**
     * Method to check if the temperature is within the minimum and maximum temperature range set
     * @return boolean to indicate if the temperature is currently in range
     */
    public boolean isInRange() {
        return celciusValue <= maxRange &&
                celciusValue >= minRange &&
                !Double.isNaN(minRange) &&
                !Double.isNaN(maxRange);
    }

    /**
     * Method to check if the celsius temperature value has been set
     * @return boolean ture if it has been set
     */
    public boolean isCelciusValueSet() {
        return !Double.isNaN(celciusValue);
    }

    /**
     * Method to check if the maximum and minimum temperature range value has been set
     * @return
     */
    public boolean isRangeSet() {
        return !Double.isNaN(minRange) && !Double.isNaN(maxRange);
    }

    /**
     * Method to get the temperature value in Fahrenheit
     * @return Fahrenheit temperature value
     */
    public double getFarenheitValue() {
        return celciusValue * 1.8 + 32;
    }

    /**
     * Method to get the temperature value in celsius
     * @return Celsius temperature value
     */
    public double getCelciusValue() {
        return celciusValue;
    }

    /**
     * Method to get the currently set minimum temperature range
     * @return value of minimum temperature range
     */
    public double getMinRange() {
        return minRange;
    }

    /**
     * Method to get the currently set maximum temperature range
     * @return value of the maximum temperature range
     */
    public double getMaxRange() {
        return maxRange;
    }
}
