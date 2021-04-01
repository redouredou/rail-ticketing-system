package org.example.model;

public class Price implements Comparable<Price> {
    public static Price Free = new Price(0.0);
    private final double value;

    public Price(double value) {
        this.value = value;
    }

    public int convertEuroToCents(){
        return (int)(Math.round(value * 100));
    }

    @Override
    public int compareTo(Price o) {
        return Double.compare(this.value, o.value);
    }
}
