package com.teamplantpower.team_plant_power;

import org.junit.Test;
import org.junit.Before;

//import static com.teamplantpower.team_plant_power.Humidity.*;
import static org.junit.Assert.*;

/**
 * Created by Brandon on 2017-06-06.
 */

public class HumidityUnitTest {
    private Humidity humidity1;
    private Humidity humidity2;
    private Humidity humidity3;
    private Humidity humidity4;
    private Humidity humidity5;
    private Humidity humidity6;
    private Range r1;
    private Range r2;
    private Range r3;
    private Range r4;
    private Range r5;
    private Range r6;



    @Before
    public void setup(){
        r1 = new Range(0,101);
        r2 = new Range(-100,50);
        r3 = new Range(85,90);
        r4 = new Range(90,85);
        r5 = new Range(90,90);
        r6 = new Range(35.7,90.9);

        humidity1 = new Humidity(-7);
        humidity2 = new Humidity(107);
        humidity3 = new Humidity(5);
        humidity4 = new Humidity(85);
        humidity5 = new Humidity(90.0);
        humidity6 = new Humidity(45.1);
    }
    @Test
    public void checkHumidityIsValid() throws Exception{
        //Test to ensure humidity >= 0 and <= 100
        assertFalse(humidity1.validateHumidity());
        assertFalse(humidity2.validateHumidity());
        assertTrue(humidity3.validateHumidity());
        assertTrue(humidity4.validateHumidity());
        assertTrue(humidity5.validateHumidity());
        assertTrue(humidity6.validateHumidity());
    }
    @Test
    public void checkRangeIsValid() throws Exception{
        //Test to ensure minRange is less than or equal to maxRange and minRange >=0 and maxRange <=100
        assertFalse(r1.validateRange());
        assertFalse(r2.validateRange());
        assertTrue(r3.validateRange());
        assertFalse(r4.validateRange());
        assertTrue(r5.validateRange());
        assertTrue(r5.validateRange());



    }
    @Test
    public void checkHumidityWithinRange()throws Exception{
        //Test to ensure that the current humidity is within the given range
        assertFalse(r1.isInRange(humidity1.getPercentHumidity()));
        assertFalse(r2.isInRange(humidity2.getPercentHumidity()));
        assertFalse(r3.isInRange(humidity3.getPercentHumidity()));
        assertFalse(r4.isInRange(humidity4.getPercentHumidity()));
        assertTrue(r5.isInRange(humidity5.getPercentHumidity()));
        assertTrue(r6.isInRange(humidity6.getPercentHumidity()));

    }

}
