package com.urbanandco.berlintime;

/**
 * Interface to allow pluggable formatters
 * <p>
 * Created by istvan on 10/03/2016.
 */
public interface BerlinTimeFormat {
    String format(BerlinTime time);
}
