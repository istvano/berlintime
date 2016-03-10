package com.urbanandco.berlintime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BerlinTimeTest {

    @Test(expected = IllegalArgumentException.class)
    public void berlinTimeThrowsExceptionForHours() {
        new BerlinTime(24, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void berlinTimeThrowsExceptionForMinute() {
        new BerlinTime(1, -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void berlinTimeThrowsExceptionForSecond() {
        new BerlinTime(10, 0, 60);
    }

    @Test()
    public void berlinTimeParameterTest() {
        BerlinTime bt = new BerlinTime(10, 11, 12);
        assertEquals("hour", 10, bt.getHour());
        assertEquals("minute", 11, bt.getMinute());
        assertEquals("second", 12, bt.getSecond());
    }

    @Test()
    public void berlinTimeSingleSecondsTest() {
        BerlinTime bt = new BerlinTime(1, 1, 12);
        assertEquals("second 12", true, bt.getSingleSecond());

        bt = new BerlinTime(1, 1, 9);
        assertEquals("second 9", false, bt.getSingleSecond());
    }

    @Test()
    public void berlinTimeFiveHoursTest() {
        BerlinTime bt = new BerlinTime(4, 0, 0);
        assertEquals("Five hour 04:00:00", Arrays.asList(new Boolean[]{false, false, false, false}), bt.getFiveHours());

        bt = new BerlinTime(6, 0, 0);
        assertEquals("Five hour 6:00:00", Arrays.asList(new Boolean[]{true, false, false, false}), bt.getFiveHours());

        bt = new BerlinTime(10, 0, 0);
        assertEquals("Five hour 10:00:00", Arrays.asList(new Boolean[]{true, true, false, false}), bt.getFiveHours());

        bt = new BerlinTime(18, 0, 0);
        assertEquals("Five hour 18:00:00", Arrays.asList(new Boolean[]{true, true, true, false}), bt.getFiveHours());

        bt = new BerlinTime(21, 0, 0);
        assertEquals("Five hour 21:00:00", Arrays.asList(new Boolean[]{true, true, true, true}), bt.getFiveHours());

    }

    @Test()
    public void berlinTimeSingleHoursTest() {
        BerlinTime bt = new BerlinTime(5, 0, 0);
        assertEquals("Single hour 05:00:00", Arrays.asList(new Boolean[]{false, false, false, false}), bt.getSingleHours());

        bt = new BerlinTime(6, 0, 0);
        assertEquals("Single hour 06:00:00", Arrays.asList(new Boolean[]{true, false, false, false}), bt.getSingleHours());

        bt = new BerlinTime(7, 0, 0);
        assertEquals("Single hour 07:00:00", Arrays.asList(new Boolean[]{true, true, false, false}), bt.getSingleHours());

        bt = new BerlinTime(8, 0, 0);
        assertEquals("Single hour 08:00:00", Arrays.asList(new Boolean[]{true, true, true, false}), bt.getSingleHours());

        bt = new BerlinTime(9, 0, 0);
        assertEquals("Single hour 09:00:00", Arrays.asList(new Boolean[]{true, true, true, true}), bt.getSingleHours());

    }

    @Test()
    public void berlinTimeMinutesTest() {
        BerlinTime bt = new BerlinTime(0, 59, 0);
        assertEquals("five minutes  00:59:00", Arrays.asList(new Boolean[]{true, true, true, true, true, true, true, true, true, true, true}), bt.getFiveMinutes());
        assertEquals("single minutes 00:59:00", Arrays.asList(new Boolean[]{true, true, true, true}), bt.getSingleMinutes());

        bt = new BerlinTime(0, 13, 0);
        assertEquals("five minutes 00:13:00", Arrays.asList(new Boolean[]{true, true, false, false, false, false, false, false, false, false, false}), bt.getFiveMinutes());
        assertEquals("single minutes 00:13:00", Arrays.asList(new Boolean[]{true, true, true, false}), bt.getSingleMinutes());

        bt = new BerlinTime(0, 35, 0);
        assertEquals("five minutes 00:35:00", Arrays.asList(new Boolean[]{true, true, true, true, true, true, true, false, false, false, false}), bt.getFiveMinutes());
        assertEquals("single minutes 00:35:00", Arrays.asList(new Boolean[]{false, false, false, false}), bt.getSingleMinutes());

    }

}