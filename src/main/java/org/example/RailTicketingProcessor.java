package org.example;

import org.example.model.*;
import org.example.services.TripServices;
import org.example.services.TripServicesImpl;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RailTicketingProcessor {

    private RailTicketingProcessor(){
        throw new IllegalArgumentException("RailTicketingProcessor class");
    }

    private static final TripServices tripServices = new TripServicesImpl();

    
    public static void run(String inputFilePath, String outputFilePath){
        RootInput jsonInput = Utils.parseJSONFile(inputFilePath);

        List<Integer> customerIdList= jsonInput.getTaps().stream().map(Tap::getCustomerId).distinct().collect(Collectors.toList());

        List<CustomerSummary> customerSummaries = new ArrayList<>();

        customerIdList.forEach(customerIdElt -> {
                    List<Tap> tapByCustomerList = jsonInput
                            .getTaps()
                            .stream()
                            .filter(tap -> tap.getCustomerId() == customerIdElt).collect(Collectors.toList());
            final int chunkSize = 2;
            final AtomicInteger counter = new AtomicInteger();

            final Collection<List<Tap>> result = tapByCustomerList.stream()
                    .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
                    .values();

            List<Trip> trips = new ArrayList<>();
            result.forEach(coupleTap ->
                    trips.add(tripServices.updateTrip(
                            new Trip.Builder(
                                    coupleTap.get(0).getStation(),
                                    coupleTap.get(1).getStation(),
                                    coupleTap.get(0).getUnixTimestamp())
                                    .build()
                    )));

            CustomerSummary customerSummary = new CustomerSummary(
                    customerIdElt,
                    trips.stream().map(Trip::getCostInCents).reduce(0.0, Double::sum),
                    trips);

            customerSummaries.add(customerSummary);
        });



        Utils.generateOutputFile(new RootOutput(customerSummaries), outputFilePath);
    }
}