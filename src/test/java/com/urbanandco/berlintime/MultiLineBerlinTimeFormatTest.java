package com.urbanandco.berlintime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MultiLineBerlinTimeFormatTest {

    @Test()
    public void formatSecondsTest() {
        MultiLineBerlinTimeFormat formatter = new MultiLineBerlinTimeFormat();

        BerlinTime bt = new BerlinTime(0, 0, 0);
        assertEquals("seconds 00:00:00", "Y", extractLine(formatter.format(bt), 0));

        bt = new BerlinTime(0, 0, 59);
        assertEquals("seconds 00:00:59", "-", extractLine(formatter.format(bt), 0));
    }

    @Test()
    public void formatFiveMinutesTest() {
        MultiLineBerlinTimeFormat formatter = new MultiLineBerlinTimeFormat();

        BerlinTime bt = new BerlinTime(0, 0, 0);
        assertEquals("five minutes 00:00:00", "-----------", extractLine(formatter.format(bt), 3));

        bt = new BerlinTime(0, 05, 59);
        assertEquals("five minutes 00:05:59", "Y----------", extractLine(formatter.format(bt), 3));

        bt = new BerlinTime(0, 10, 59);
        assertEquals("five minutes 00:10:59", "YY---------", extractLine(formatter.format(bt), 3));

        bt = new BerlinTime(0, 15, 59);
        assertEquals("five minutes 00:15:59", "YYR--------", extractLine(formatter.format(bt), 3));

        bt = new BerlinTime(0, 25, 59);
        assertEquals("five minutes 00:25:59", "YYRYY------", extractLine(formatter.format(bt), 3));

        bt = new BerlinTime(0, 40, 59);
        assertEquals("five minutes 00:25:59", "YYRYYRYY---", extractLine(formatter.format(bt), 3));

        bt = new BerlinTime(0, 59, 59);
        assertEquals("five minutes 00:59:59", "YYRYYRYYRYY", extractLine(formatter.format(bt), 3));
    }

    @Test()
    public void formatSingleMinutesTest() {
        MultiLineBerlinTimeFormat formatter = new MultiLineBerlinTimeFormat();

        BerlinTime bt = new BerlinTime(0, 0, 0);
        assertEquals("single minute 00:00:00", "----", extractLine(formatter.format(bt), 4));

        bt = new BerlinTime(0, 59, 59);
        assertEquals("single minute 00:59:59", "YYYY", extractLine(formatter.format(bt), 4));

        bt = new BerlinTime(0, 32, 59);
        assertEquals("single minute 00:32:59", "YY--", extractLine(formatter.format(bt), 4));

        bt = new BerlinTime(0, 43, 59);
        assertEquals("single minute 00:43:59", "YYY-", extractLine(formatter.format(bt), 4));
    }

    @Test()
    public void formatFiveHourTest() {
        MultiLineBerlinTimeFormat formatter = new MultiLineBerlinTimeFormat();

        BerlinTime bt = new BerlinTime(0, 0, 0);
        assertEquals("Five hour 00:00:00", "----", extractLine(formatter.format(bt), 1));

        bt = new BerlinTime(23, 59, 59);
        assertEquals("Five hour 23:59:59", "RRRR", extractLine(formatter.format(bt), 1));

        bt = new BerlinTime(10, 32, 59);
        assertEquals("Five hour 10:32:59", "RR--", extractLine(formatter.format(bt), 1));

        bt = new BerlinTime(15, 43, 59);
        assertEquals("Five hour 15:43:59", "RRR-", extractLine(formatter.format(bt), 1));
    }

    @Test()
    public void formatSingleHourTest() {
        MultiLineBerlinTimeFormat formatter = new MultiLineBerlinTimeFormat();

        BerlinTime bt = new BerlinTime(0, 0, 0);
        assertEquals("Single hour 00:00:00", "----", extractLine(formatter.format(bt), 2));

        bt = new BerlinTime(10, 0, 0);
        assertEquals("Single hour 10:00:00", "----", extractLine(formatter.format(bt), 2));

        bt = new BerlinTime(15, 0, 0);
        assertEquals("Single hour 15:00:00", "----", extractLine(formatter.format(bt), 2));

        bt = new BerlinTime(23, 59, 59);
        assertEquals("Single hour 23:59:59", "RRR-", extractLine(formatter.format(bt), 2));

        bt = new BerlinTime(19, 32, 59);
        assertEquals("Single hour 19:32:59", "RRRR", extractLine(formatter.format(bt), 2));

        bt = new BerlinTime(07, 43, 59);
        assertEquals("Single hour 07:43:59", "RR--", extractLine(formatter.format(bt), 2));

        bt = new BerlinTime(01, 43, 59);
        assertEquals("Single hour 01:43:59", "R---", extractLine(formatter.format(bt), 2));
    }

    /**
     * Helper function to extract a single line from a multiline BerlinTime string.
     * Second, 5 hours block, 1 hour block, 5 minutes block, 1 minute block
     * E.g. 13:28:12
     * Y
     * RR--
     * RRR-
     * YYRYY------
     * YYY-
     *
     * @param str
     * @param lineNr
     * @return
     */
    private String extractLine(String str, int lineNr) {
        String lines[] = str.split("\\r?\\n");
        if (lines.length < lineNr) {
            return "";
        } else {
            return lines[lineNr].trim();
        }
    }

}