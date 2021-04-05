package org.example.inputmodel;

import java.util.Objects;

public class Price implements Comparable<Price> {
    public static final Price Free = new Price(0.0);
    private final double value;

    public Price(double value) {
        if(value < 0){
            throw new IllegalArgumentException("The price can't be negative");
        }
        this.value = value;
    }

    public int convertEuroToCents(){
        return (int)(Math.round(value * 100));
    }

    @Override
    public int compareTo(Price o) {
        return Double.compare(this.value, o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Double.compare(price.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                '}';
    }
}
