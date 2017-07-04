package com.teamplantpower.team_plant_power;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by Mishal on 08/06/2017.
 */

public class LightUnitTest {
    private Light light1;
    private Light light2;
    private Light light3;
    private Light light4;
    private Light light5;
    private Light light6;
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

        light1 = new Light(-7);
        light2 = new Light(107);
        light3 = new Light(5);
        light4 = new Light(85);
        light5 = new Light(90.0);
        light6 = new Light(45.1);
    }
    @Test
    public void checkLightIsValid() throws Exception{
        //Test to ensure light >= 0 and <= 100
        assertFalse(light1.validateLight());
        assertFalse(light2.validateLight());
        assertTrue(light3.validateLight());
        assertTrue(light4.validateLight());
        assertTrue(light5.validateLight());
        assertTrue(light6.validateLight());
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
    public void checkLightWithinRange()throws Exception{
        //Test to ensure that the current light is within the given range
        assertFalse(r1.isInRange(light1.getPercentLight()));
        assertFalse(r2.isInRange(light2.getPercentLight()));
        assertFalse(r3.isInRange(light3.getPercentLight()));
        assertFalse(r4.isInRange(light4.getPercentLight()));
        assertTrue(r5.isInRange(light5.getPercentLight()));
        assertTrue(r6.isInRange(light6.getPercentLight()));

    }

}
