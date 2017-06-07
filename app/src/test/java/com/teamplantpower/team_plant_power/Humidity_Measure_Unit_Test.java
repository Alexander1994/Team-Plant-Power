package com.teamplantpower.team_plant_power;

import org.junit.Test;
import org.junit.Before;

import static com.teamplantpower.team_plant_power.HumidityMeasure.*;
import static org.junit.Assert.*;

/**
 * Created by Brandon on 2017-06-06.
 */

public class Humidity_Measure_Unit_Test {
    private HumidityMeasure humidityMeasure1;
    private HumidityMeasure humidityMeasure2;
    private HumidityMeasure humidityMeasure3;
    private HumidityMeasure humidityMeasure4;
    private HumidityMeasure humidityMeasure5;
    private HumidityMeasure humidityMeasure6;


    @Before
    public void setup(){
        humidityMeasure1 = new HumidityMeasure(-7, 0, 101);
        humidityMeasure2 = new HumidityMeasure(107, -100, 50);
        humidityMeasure3 = new HumidityMeasure(5, 85, 90);
        humidityMeasure4 = new HumidityMeasure(85.9, 90, 85);
        humidityMeasure5 = new HumidityMeasure(90.0, 90, 90);
        humidityMeasure6 = new HumidityMeasure(45.1, 35.7, 90.9);
    }
    @Test
    public void checkHumidityIsValid() throws Exception{
        //Cheeck humidity >= 0 and <= 100
        assertFalse(validateHumidity(humidityMeasure1));
        assertFalse(validateHumidity(humidityMeasure2));
        assertTrue(validateHumidity(humidityMeasure3));
        assertTrue(validateHumidity(humidityMeasure4));
        assertTrue(validateHumidity(humidityMeasure5));
        assertTrue(validateHumidity(humidityMeasure6));
    }
    @Test
    public void checkRangeIsValid() throws Exception{
        //This is to test the the minRange is less than or equal to maxRange
        //Check minRange >=0 and maxRange <=100
        assertFalse(validateHumidityRange(humidityMeasure1));
        assertFalse(validateHumidityRange(humidityMeasure2));
        assertTrue(validateHumidityRange(humidityMeasure3));
        assertFalse(validateHumidityRange(humidityMeasure4));
        assertTrue(validateHumidityRange(humidityMeasure5));
        assertTrue(validateHumidityRange(humidityMeasure6));



    }
    @Test
    public void checkHumidityWithinRange()throws Exception{
        //Check that the current humidity is within the given range
        assertFalse(checkHumidityInRange(humidityMeasure1));
        assertFalse(checkHumidityInRange(humidityMeasure2));
        assertFalse(checkHumidityInRange(humidityMeasure3));
        assertFalse(checkHumidityInRange(humidityMeasure4));
        assertTrue(checkHumidityInRange(humidityMeasure5));
        assertTrue(checkHumidityInRange(humidityMeasure6));

    }

}
