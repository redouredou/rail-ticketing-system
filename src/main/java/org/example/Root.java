package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Root{

    @JsonProperty("taps")
    List<Tap> taps;

    public List<Tap> getTaps() {
        return this.taps;
    }

    public void setTaps(List<Tap> taps) {
        this.taps = taps;
    }

}