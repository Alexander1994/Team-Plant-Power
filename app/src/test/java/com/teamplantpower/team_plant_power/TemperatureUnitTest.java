package com.teamplantpower.team_plant_power;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureUnitTest {
    Temperature t;
    Range r;

    @Before
    public void setup(){
        t = new Temperature();
        r = new Range();
    }
    @Test
    public void rangeIsValid() throws Exception {

        r.setMinRange(30);
        r.setMaxRange(20);
        assertEquals(r.validateRange(), false);
        r.setMinRange(10);
        r.setMaxRange(30);
        assertEquals(r.validateRange(), true);
    }
    @Test
    public void rangeIsNotSetBeforeValue() throws Exception {
        t.setCelciusValue(20);
        assertEquals(r.isInRange(t.getCelciusValue()), false);
    }
    @Test
    public void valueIsBeyondMax() throws Exception {
        r.setMinRange(10);
        r.setMaxRange(30);
        t.setCelciusValue(40);
        assertEquals(r.isInRange(t.getCelciusValue()), false);
    }
    @Test
    public void valueIsLessThanMin() throws Exception {
        r.setMinRange(10);
        r.setMaxRange(30);
        t.setCelciusValue(5);
        assertEquals(r.isInRange(t.getCelciusValue()), false);
    }
    @Test
    public void farenheitConversionOfTemperature() throws Exception {
        r.setMinRange(10);
        r.setMaxRange(30);
        t.setCelciusValue(20);
        assertEquals(t.getFarenheitValue() == 68.0, true);
    }

    @Test
    public void isInRangeTest() throws Exception {
        r.resetRange();
        assertEquals(r.isRangeSet(), false);
        r.setMinRange(0);
        assertEquals(r.isRangeSet(), false);
        r.setMaxRange(30);
        assertEquals(r.isRangeSet(), true);
    }
}
