package com.teamplantpower.team_plant_power;

import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureUnitTest {

    @Test
    public void rangeIsValid() throws Exception {
        Temperature t = new Temperature();
        assertEquals(t.setRange(20, 10), false);
    }
    @Test
    public void rangeIsNotSetBeforeValue() throws Exception {
        Temperature t = new Temperature();
        t.setCelciusValue(20);
        assertEquals(t.isInRange(), false);
    }
    @Test
    public void valueIsBeyondMax() throws Exception {
        Temperature t = new Temperature();
        t.setRange(10,30);
        t.setCelciusValue(40);
        assertEquals(t.isInRange(), false);
    }
    @Test
    public void valueIsLessThanMin() throws Exception {
        Temperature t = new Temperature();
        t.setRange(10,30);
        t.setCelciusValue(5);
        assertEquals(t.isInRange(), false);
    }
    @Test
    public void farenheitConversionOfTemperature() throws Exception {
        Temperature t = new Temperature();
        t.setRange(10, 30);
        t.setCelciusValue(20);
        assertEquals(t.getFarenheitValue() == 68, true);
    }
    @Test
    public void checkToSeeIsValueSetIsValid() throws Exception {
        Temperature t = new Temperature();
        assertEquals(t.isCelciusValueSet(), false);
    }
}
