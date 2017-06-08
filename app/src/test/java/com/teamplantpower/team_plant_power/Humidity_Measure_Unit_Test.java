package com.teamplantpower.team_plant_power;

import org.junit.Test;
import org.junit.Before;

//import static com.teamplantpower.team_plant_power.HumidityMeasure.*;
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
        //Test to ensure humidity >= 0 and <= 100
        assertFalse(humidityMeasure1.validateHumidity());
        assertFalse(humidityMeasure2.validateHumidity());
        assertTrue(humidityMeasure3.validateHumidity());
        assertTrue(humidityMeasure4.validateHumidity());
        assertTrue(humidityMeasure5.validateHumidity());
        assertTrue(humidityMeasure6.validateHumidity());
    }
    @Test
    public void checkRangeIsValid() throws Exception{
        //Test to ensure minRange is less than or equal to maxRange and minRange >=0 and maxRange <=100
        assertFalse(humidityMeasure1.validateHumidityRange());
        assertFalse(humidityMeasure2.validateHumidityRange());
        assertTrue(humidityMeasure3.validateHumidityRange());
        assertFalse(humidityMeasure4.validateHumidityRange());
        assertTrue(humidityMeasure5.validateHumidityRange());
        assertTrue(humidityMeasure6.validateHumidityRange());



    }
    @Test
    public void checkHumidityWithinRange()throws Exception{
        //Test to ensure that the current humidity is within the given range
        assertFalse(humidityMeasure1.checkHumidityInRange());
        assertFalse(humidityMeasure2.checkHumidityInRange());
        assertFalse(humidityMeasure3.checkHumidityInRange());
        assertFalse(humidityMeasure4.checkHumidityInRange());
        assertTrue(humidityMeasure5.checkHumidityInRange());
        assertTrue(humidityMeasure6.checkHumidityInRange());

    }

}
