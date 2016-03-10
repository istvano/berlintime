package com.urbanandco.berlintime;

import java.lang.reflect.Array;
import java.util.*;

/**
 * The class <code>BerlinTime</code> represents a specific instant
 * in time using BerlintTime notation
 * <p>
 * Created by istvan on 09/03/2016.
 */
public class BerlinTime {

    private int hour;
    private int minute;
    private int second;

    /**
     * Creates a <code>BerlinTime</code> object and initializes it to
     * represent the time specified by the parameters
     *
     * @param hour   hour parameter 0-23 format
     * @param minute minute parameter 0-59 format
     * @param second second parameter 0-59 format
     * @throws java.lang.IllegalArgumentException when hour, minute, second parameter is outside of their range
     */
    public BerlinTime(int hour, int minute, int second) {

        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Hour must be in the range of [0-23]: " + hour);
        }

        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minute must be in the range of [0-59]: " + minute);
        }

        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("Second must be in the range of [0-59]: " + second);
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * Convenience constructor to creates a <code>BerlinTime</code> object and initializes it to
     * represent the <i>actual time</i><
     */
    public BerlinTime() {
        Date now = new Date();

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(now);

        //creating an instance with the actual time
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
    }

    /**
     * Returns a value of hours
     * The value returned is between <code>0</code> and <code>23</code>
     *
     * @return minute represented by this instance
     */
    public int getHour() {
        return hour;
    }

    /**
     * Returns a value of minutes
     * The value returned is between <code>0</code> and <code>59</code>
     *
     * @return minute represented by this instance
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Returns a value of seconds
     * The value returned is between <code>0</code> and <code>59</code>
     *
     * @return second represented by this instance
     */
    public int getSecond() {
        return second;
    }

    /**
     * Returns true if the top Seconds pump is active
     * true should represent even numbers an an active seconds flag
     *
     * @return boolean
     */
    public Boolean getSingleSecond() {
        return this.second % 2 == 0;
    }

    private List<Boolean> createLampArray(int arraySize, int activeItems) {
        Boolean[] result = new Boolean[arraySize];
        Arrays.fill(result, false);
        for (int i = 0; i < activeItems; i++) {
            result[i] = true;
        }
        return Arrays.asList(result);
    }


    /**
     * Returns an List with boolean values representing each top portion of Lamps
     * displaying hours with state active / inactive
     *
     * @return List<Boolean>
     */
    public List<Boolean> getFiveHours() {
        int hours = this.hour / 5;
        return this.createLampArray(4, hours);
    }

    /**
     * Returns an List with boolean values representing each bottom portion of Lamps
     * displaying hours with state active / inactive
     *
     * @return List<Boolean>
     */
    public List<Boolean> getSingleHours() {
        int hours = this.hour % 5;
        return this.createLampArray(4, hours);
    }

    /**
     * Returns an List with boolean values representing each top portion of Lamps
     * displaying minutes with state active / inactive
     *
     * @return List<Boolean>
     */
    public List<Boolean> getFiveMinutes() {
        int mins = this.minute / 5;
        return this.createLampArray(11, mins);
    }

    /**
     * Returns an List with boolean values representing each bottom portion of Lamps
     * displaying minutes with state active / inactive
     *
     * @return List<Boolean>
     */
    public List<Boolean> getSingleMinutes() {
        int mins = this.minute % 5;
        return this.createLampArray(4, mins);
    }

    @Override
    public String toString() {
        return "BerlinTime{" +
                "hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BerlinTime that = (BerlinTime) o;

        if (hour != that.hour) return false;
        if (minute != that.minute) return false;
        if (second != that.second) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hour;
        result = 31 * result + minute;
        result = 31 * result + second;
        return result;
    }
}
