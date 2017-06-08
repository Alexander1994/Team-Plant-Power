package com.teamplantpower.team_plant_power;

import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureUnitTest {

    @Test
    public void rangeIsValid() throws Exception {
        Temperature t = new Temperature();
        t.setMinRange(30);
        assertEquals(t.setMaxRange(20), false);
        t.setMaxRange(30);
        assertEquals(t.setMinRange(40), false);
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
        t.setMinRange(10);
        t.setMaxRange(30);
        t.setCelciusValue(40);
        assertEquals(t.isInRange(), false);
    }
    @Test
    public void valueIsLessThanMin() throws Exception {
        Temperature t = new Temperature();
        t.setMinRange(10);
        t.setMaxRange(30);
        t.setCelciusValue(5);
        assertEquals(t.isInRange(), false);
    }
    @Test
    public void farenheitConversionOfTemperature() throws Exception {
        Temperature t = new Temperature();
        t.setMinRange(10);
        t.setMaxRange(30);
        t.setCelciusValue(20);
        assertEquals(t.getFarenheitValue() == 68.0, true);
    }
    @Test
    public void checkToSeeIsValueSetIsValid() throws Exception {
        Temperature t = new Temperature();
        assertEquals(t.isCelciusValueSet(), false);
    }
    @Test
    public void isInRangeTest() throws Exception {
        Temperature t = new Temperature();
        assertEquals(t.isRangeSet(), false);
        t.setMinRange(0);
        assertEquals(t.isRangeSet(), false);
        t.setMaxRange(30);
        assertEquals(t.isRangeSet(), true);
    }
}
