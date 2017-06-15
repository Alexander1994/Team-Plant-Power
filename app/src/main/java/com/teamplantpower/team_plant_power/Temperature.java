package com.teamplantpower.team_plant_power;


public class Temperature {
    double minRange, maxRange, celciusValue;

    Temperature() {
        minRange = Double.NaN;
        maxRange = Double.NaN;
        celciusValue = Double.NaN;
    }

    // Sets
    public boolean setMinRange(double min) {
        if (min<= maxRange || Double.isNaN(maxRange)) {
            minRange = min;
            return true;
        }
        return false;
    }
    public boolean setMaxRange(double max) {
        if (max >= minRange || Double.isNaN(minRange)) {
            maxRange = max;
            return true;
        }
        return false;
    }
    public void resetRange() {
        minRange = Double.NaN;
        maxRange = Double.NaN;
    }
    public void setCelciusValue(double val){
        celciusValue = val;
    }

    // Checks
    public boolean isInRange() {
        return celciusValue <= maxRange &&
                celciusValue >= minRange &&
                !Double.isNaN(minRange) &&
                !Double.isNaN(maxRange);
    }
    public boolean isCelciusValueSet() {
        return !Double.isNaN(celciusValue);
    }
    public boolean isRangeSet() {
        return !Double.isNaN(minRange) && !Double.isNaN(maxRange);
    }

    // Gets
    public double getFarenheitValue() {
        return celciusValue * 1.8 + 32;
    }
    public double getCelciusValue() {
        return celciusValue;
    }
    public double getMinRange() {
        return minRange;
    }
    public double getMaxRange() {
        return maxRange;
    }
}
