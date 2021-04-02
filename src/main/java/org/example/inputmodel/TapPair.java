package org.example.inputmodel;

import java.util.Objects;

public class TapPair {

    private Tap tapStart;
    private Tap tapEnd;

    public TapPair(Tap tapStart, Tap tapEnd){
        this.tapStart = tapStart;
        this.tapEnd = tapEnd;
    }

    public Tap getTapStart() {
        return tapStart;
    }

    public void setTapStart(Tap tapStart) {
        this.tapStart = tapStart;
    }

    public Tap getTapEnd() {
        return tapEnd;
    }

    public void setTapEnd(Tap tapEnd) {
        this.tapEnd = tapEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TapPair tapPair = (TapPair) o;
        return tapStart.equals(tapPair.tapStart) && tapEnd.equals(tapPair.tapEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tapStart, tapEnd);
    }
}
