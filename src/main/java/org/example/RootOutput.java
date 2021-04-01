package org.example;

import java.util.List;

public class RootOutput {

    private final List<CustomerSummary> customerSummaries;

    public RootOutput(List<CustomerSummary> customerSummaries){
        this.customerSummaries = customerSummaries;
    }

    public List<CustomerSummary> getCustomerSummaries() {
        return customerSummaries;
    }
}
