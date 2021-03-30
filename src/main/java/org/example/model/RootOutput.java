package org.example.model;

import java.util.List;

public class RootOutput {

    private List<CustomerSummarie> customerSummaries;

    public RootOutput(List<CustomerSummarie> customerSummaries){
        this.customerSummaries = customerSummaries;
    }

    public List<CustomerSummarie> getCustomerSummaries() {
        return customerSummaries;
    }
}
