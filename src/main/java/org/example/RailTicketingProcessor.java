package org.example;

import org.example.inputmodel.RootInput;
import org.example.inputmodel.Tap;
import org.example.inputmodel.TapPair;
import org.example.outputmodel.CustomerSummary;
import org.example.outputmodel.RootOutput;
import org.example.outputmodel.Trip;
import org.example.util.Utils;
import java.util.*;
import java.util.stream.Collectors;

public class RailTicketingProcessor {

    private RailTicketingProcessor(){
        throw new IllegalArgumentException("RailTicketingProcessor class");
    }

    public static void run(String inputFilePath, String outputFilePath){
        RootInput jsonInput = Utils.parseJSONFile(inputFilePath);

        List<CustomerSummary> customerSummaries = buildCustomerSummaries(jsonInput);

        Utils.generateOutputFile(new RootOutput(customerSummaries), outputFilePath);
    }

    private static List<CustomerSummary> buildCustomerSummaries(RootInput jsonInput){
        Map<Integer, Set<Tap>> customersTaps = jsonInput.getCustomersTaps();

        List<CustomerSummary> customerSummaries = new ArrayList<>();

        customersTaps.entrySet().forEach(entry -> {
            List<Tap> allTapsByCustomer = entry.getValue()
                    .stream()
                    .sorted(Comparator.comparingInt(Tap::getUnixTimestamp))
                    .collect(Collectors.toList());

            List<TapPair> allTapPairsByCustomer = Utils.partitionBasedOnSize(allTapsByCustomer, 2)
                    .stream()
                    .map(tapPair -> new TapPair(tapPair.get(0), tapPair.get(1)))
                    .collect(Collectors.toList());

            List<Trip> trips = allTapPairsByCustomer
                    .stream()
                    .map(tapPair -> Trip.updateTrip(new Trip.Builder(tapPair.getTapStart().getStation(), tapPair.getTapEnd().getStation(), tapPair.getTapStart().getUnixTimestamp()).build()))
                    .collect(Collectors.toList());

            CustomerSummary customerSummary = new CustomerSummary(
                    entry.getKey(),
                    trips.stream().map(Trip::getCostInCents).reduce(0, Integer::sum),
                    trips);

            customerSummaries.add(customerSummary);
        });

        return customerSummaries;
    }
}