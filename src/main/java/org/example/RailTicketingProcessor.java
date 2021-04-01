package org.example;

import org.example.model.*;
import org.example.services.TripServices;
import org.example.services.TripServicesImpl;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RailTicketingProcessor {

    private RailTicketingProcessor(){
        throw new IllegalArgumentException("RailTicketingProcessor class");
    }

    private static final TripServices tripServices = new TripServicesImpl();

    public static void run(String inputFilePath, String outputFilePath){
        RootInput jsonInput = Utils.parseJSONFile(inputFilePath);

        List<CustomerSummary> customerSummaries = buildCustomerSummaries(jsonInput);

        Utils.generateOutputFile(new RootOutput(customerSummaries), outputFilePath);
    }

    private static List<CustomerSummary> buildCustomerSummaries(RootInput jsonInput){
        Map<Integer, Set<Tap>> customersTaps = jsonInput.getCustomersTaps();
//        Set<Integer> customerIds= jsonInput.getTaps().stream().map(Tap::getCustomerId).collect(Collectors.toSet());

        List<CustomerSummary> customerSummaries = new ArrayList<>();

        customersTaps.entrySet().forEach(entry -> {
            Set<Tap> allTapsByCustomer = entry.getValue();

            final int chunkSize = 2;
            final AtomicInteger counter = new AtomicInteger();
            final Set<TapPair> allTapPairsByCustomer = allTapsByCustomer.stream()
                    .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
                    .values()
                    .stream()
                    .map(tapPair -> new TapPair(tapPair.get(0), tapPair.get(1)))
                    .collect(Collectors.toSet());

            List<Trip> trips = new ArrayList<>();
            allTapPairsByCustomer.forEach(tapPair ->
                    trips.add(tripServices.updateTrip(
                            new Trip.Builder(
                                    tapPair.getTapStart().getStation(),
                                    tapPair.getTapEnd().getStation(),
                                    tapPair.getTapStart().getUnixTimestamp())
                                    .build()
                    )));

            CustomerSummary customerSummary = new CustomerSummary(
                    entry.getKey(),
                    trips.stream().map(Trip::getCostInCents).reduce(0, Integer::sum),
                    trips);

            customerSummaries.add(customerSummary);
        });

        return customerSummaries;
    }
}