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
    public boolean setCelciusValue(double val){
        if (val <= maxRange &&
                val >= minRange &&
                !Double.isNaN(minRange) &&
                !Double.isNaN(maxRange)) {
            celciusValue = val;
            return true;
        }
        return false;
    }
    public double getFarenheitValue() {
        return celciusValue * 1.8 + 32;
    }
    public double getCelciusValue() {
        return celciusValue;
    }
}
