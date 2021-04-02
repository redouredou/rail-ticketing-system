package org.example.inputmodel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class RootInput {

    private List<Tap> taps;

    public List<Tap> getTaps() {
        return this.taps;
    }

    public void setTaps(List<Tap> taps) {
        this.taps = taps;
    }

    public Map<Integer, Set<Tap>> getCustomersTaps() {
        return taps.stream().collect(groupingBy(Tap::getCustomerId, toSet()));
    }
}