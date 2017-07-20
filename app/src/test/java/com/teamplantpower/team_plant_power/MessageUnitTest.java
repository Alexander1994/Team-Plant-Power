package com.teamplantpower.team_plant_power;


import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class MessageUnitTest {
    private Message m1;
    private String dateStr;

    @Before
    public void setup(){
        m1 = new Message("John Cena", "Hello world!");
        dateStr = ""+(new Date(314).getTime());
    }

    @Test
    public void checkMsgKeyToDisplayStringConverter() throws Exception{
        // ensures function properly formats date for UI
        assertTrue(Message.msgKeyToDisplayStr(dateStr).equals("Dec 31 20:00"));
    }

    @Test
    public void checkRangeIsValid() throws Exception{
        // ensure display message is created in the proper format
        assertTrue(m1.getFullDisplayMessage(dateStr).equals("Dec 31 20:00, John Cena: Hello world!"));
    }


}