package com.urbanandco.berlintime;

/**
 * Sample application to demonstrate (hh:mm:ss) conversion to Berlin Time
 */
public class Main {
    /**
     * Application Entry Point
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            String arg = args[0];
            String[] params = arg.trim().split(":");
            if (params.length == 3) {
                try {
                    BerlinTime time = new BerlinTime(Integer.valueOf(params[0]), Integer.valueOf(params[1]), Integer.valueOf(params[2]));
                    System.out.println(new MultiLineBerlinTimeFormat().format(time));
                } catch (Exception e) {
                    System.out.println("BerlinTime converter, Invalid parameter value! Please supply time parameter int he following format: hh:mm:ss");
                }
            } else {
                System.out.println("BerlinTime converter, Invalid number of parameter! Please supply time parameter int he following format: hh:mm:ss");
            }

        } else {
            printHelp();
        }
    }

    private static void printHelp() {
        System.out.println("BerlinTime converter, please supply time parameter int he following format: hh:mm:ss");
    }
}