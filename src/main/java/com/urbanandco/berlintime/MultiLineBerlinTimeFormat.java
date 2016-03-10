package com.urbanandco.berlintime;

import java.util.List;

/**
 * Multiline formatter to display BerlinTime as a multi-line string using the following Colour and State
 * representation:
 * <p>
 * RED Lamp -> R
 * YELLOW Lamp -> Y
 * INACTIVE Lamp -> -
 * <p>
 * The format follows the following Specification
 * <p>
 * Berlin Clock description:
 * The time is calculated by adding the lit rectangular lamps.
 * The top lamp is a pump which is blinking on/off every two seconds.
 * In the upper line of red lamps every lamp represents 5 hours.
 * In the lower line of red lamps every lamp represents 1 hour.
 * So if in the first line 2 lamps are lit and in the second line 3 lamps its 5+5+3=13h or 1 p.m.
 * In the third line with tall lamps every lamp represents 5 minutes.
 * There are 11 lamps, the 3rd, 6th, and 9th are red indicating the first quarter, half, and the last quarter of the hour.
 * In the last line with yellow lamps every lamp represents 1 minute.​
 * <p>
 * Created by Istvan on 09/03/2016.
 */
public class MultiLineBerlinTimeFormat implements BerlinTimeFormat {

    public enum State {
        YELLOW("Y"),
        RED("R"),
        INACTIVE("-");

        private String value;

        private State(String value) {
            this.value = value;
        }
    }

    private static final String NEW_LINE = System.lineSeparator();

    /**
     * Format a BerlintTime instance into a multi-line character representation
     *
     * @param time
     * @return
     */
    @Override
    public String format(final BerlinTime time) {
        final StringBuffer buffer = new StringBuffer();

        buffer.append(formatLamp(time.getSingleSecond(), State.YELLOW, State.INACTIVE));
        buffer.append(NEW_LINE);
        buffer.append(formatLamp(time.getFiveHours(), State.RED, State.INACTIVE));
        buffer.append(NEW_LINE);
        buffer.append(formatLamp(time.getSingleHours(), State.RED, State.INACTIVE));
        buffer.append(NEW_LINE);

        // generate 5 minutes lamp
        String fiveMinutes = formatLamp(time.getFiveMinutes(), State.YELLOW, State.INACTIVE);
        //change trailing Y with R in case of a 15 minutes block ( e.g. YYY -> YYR )
        buffer.append(fiveMinutes.replaceAll(State.YELLOW.value+State.YELLOW.value+State.YELLOW.value, State.YELLOW.value+State.YELLOW.value+State.RED.value));

        buffer.append(NEW_LINE);
        buffer.append(formatLamp(time.getSingleMinutes(), State.YELLOW, State.INACTIVE));
        return buffer.toString();
    }

    /**
     * Formats an individual item using State enum
     *
     * @param active
     * @param on
     * @param off
     * @return
     */
    private String formatLamp(Boolean active, State on, State off) {
        return (active ? on : off).value;
    }

    /**
     * Build a single row of lamps from a List of Boolean elements using state parameter
     *
     * @param elements
     * @param on
     * @param off
     * @return
     */
    private String formatLamp(List<Boolean> elements, State on, State off) {
        final StringBuffer buffer = new StringBuffer();
        for (Boolean item : elements) {
            buffer.append(formatLamp(item, on, off));
        }
        return buffer.toString();
    }

}
