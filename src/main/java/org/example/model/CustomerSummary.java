package org.example.model;

import java.util.List;

public class CustomerSummary {

    private int customerId;

    private double totalCostInCents;

    List<Trip> trips;

    public CustomerSummary(int customerId, double totalCostInCents, List<Trip> trips){
        this.customerId = customerId;
        this.totalCostInCents = totalCostInCents;
        this.trips = trips;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotalCostInCents() {
        return totalCostInCents;
    }

    public void setTotalCostInCents(double totalCostInCents) {
        this.totalCostInCents = totalCostInCents;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public String toString() {
        return "CustomerSummaries{" +
                "customerId=" + customerId +
                ", totalCostInCents=" + totalCostInCents +
                ", trips=" + trips +
                '}';
    }
}
