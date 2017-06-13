package com.teamplantpower.team_plant_power;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void dbTest() throws Exception {
        Database d = new Database();
        for (int i=0; i < 40; i++) {
            double h = d.getHumidityData(), l = d.getLightData(), t=d.getTemperatureData();
            assertEquals(h <= 100.0 && h>=0, true);
            assertEquals(l <= 100.0 && l>=0, true);
            assertEquals(t <= 30.0 && t>=0, true);
        }

    }
}