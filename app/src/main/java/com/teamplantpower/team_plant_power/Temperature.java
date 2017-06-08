package com.teamplantpower.team_plant_power;


public class Temperature {
    double minRange, maxRange, celciusValue;

    Temperature() {
        minRange = Double.NaN;
        maxRange = Double.NaN;
        celciusValue = Double.NaN;
    }

    public boolean setRange(double min, double max) {
        if (min <= max) {
            minRange = min;
            maxRange = max;
            return true;
        }
        return false;
    }
    public void setCelciusValue(double val){
        celciusValue = val;
    }
    public boolean isInRange() {
        return celciusValue <= maxRange &&
                celciusValue >= minRange &&
                !Double.isNaN(minRange) &&
                !Double.isNaN(maxRange);
    }
    public boolean isCelciusValueSet() {
        return !Double.isNaN(celciusValue);
    }

    public double getFarenheitValue() {
        return celciusValue * 1.8 + 32;
    }
    public double getCelciusValue() {
        return celciusValue;
    }
}
