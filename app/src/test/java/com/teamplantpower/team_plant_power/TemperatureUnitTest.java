package com.teamplantpower.team_plant_power;

import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureUnitTest {

    @Test
    public void rangeIsValid() throws Exception {
        Temperature t new Temperature();
        assertEquals(t.setRange(20, 10), false);
    }
    @Test
    public void rangeIsNotSetBeforeValue() throws Exception {
        Temperature t new Temperature();
        assertEquals(t.setValue(20), false);
    }
    @Test
    public void valueIsBeyondMax() throws Exception {
        Temperature t new Temperature();
        t.setRange(10,30);
        assertEquals(t.setValue(40), false);
    }
    @Test
    public void valueIsLessThanMin() throws Exception {
        Temperature t new Temperature();
        t.setRange(10,30);
        assertEquals(t.setValue(5), false);
    }
    @Test
    public void farenheitConversionOfTemperature() throws Exception {
        Temperature t = new Temperature();
        t.setRange(10, 30);
        t.setCelciusValue(20);
        assertEquals(t.getFarenheitValue(), 68);
    }

}
