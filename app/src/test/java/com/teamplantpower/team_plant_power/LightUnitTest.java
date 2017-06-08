package com.teamplantpower.team_plant_power;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by Mishal on 08/06/2017.
 */

public class LightUnitTest {

    private  Light_UI  lightMeasure1;
    private  Light_UI  lightMeasure2;
    private  Light_UI  lightMeasure3;
    private  Light_UI  lightMeasure4;
    private  Light_UI  lightMeasure5;
    private  Light_UI  lightMeasure6;
  
          
    @Before
    public  void  setup() {
        lightMeasure1 = new  Light_UI(-7, 0, 101);
        lightMeasure2 = new  Light_UI(107, -100, 50);
        lightMeasure3 = new  Light_UI(5, 85, 90);
        lightMeasure4 = new  Light_UI(85.9, 90, 85);
        lightMeasure5 = new  Light_UI(90.0, 90, 90);
        lightMeasure6 = new  Light_UI(45.1, 35.7, 90.9);
    }
      @Test
      public void checkHumidityIsValid() throws Exception{
          //Test to ensure humidity >= 0 and <= 100
          assertFalse( lightMeasure1.validateLight());
          assertFalse( lightMeasure2.validateLight());
          assertTrue( lightMeasure3.validateLight());
          assertTrue( lightMeasure4.validateLight());
          assertTrue( lightMeasure5.validateLight());
          assertTrue( lightMeasure6.validateLight());
             }
      @Test
      public void checkRangeIsValid() throws Exception{
            //Test to ensure minRange is less than or equal to maxRange and minRange >=0 and maxRange <=100
          assertFalse( lightMeasure1.validateLightRange());
          assertFalse( lightMeasure2.validateLightRange());
          assertTrue( lightMeasure3.validateLightRange());
          assertFalse( lightMeasure4.validateLightRange());
          assertTrue( lightMeasure5.validateLightRange());
          assertTrue( lightMeasure6.validateLightRange());
      }
      @Test
      public void checkHumidityWithinRange()throws Exception{
          //Test to ensure that the current humidity is within the given range
          assertFalse( lightMeasure1.checkLightInRange());
          assertFalse( lightMeasure2.checkLightInRange());
          assertFalse( lightMeasure3.checkLightInRange());
          assertFalse( lightMeasure4.checkLightInRange());
          assertTrue( lightMeasure5.checkLightInRange());
          assertTrue( lightMeasure6.checkLightInRange());
        
      }
}
