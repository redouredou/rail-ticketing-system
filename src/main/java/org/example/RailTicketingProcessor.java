package org.example;

import org.example.model.Root;

public class RailTicketingProcessor {

    private RailTicketingProcessor(){
        throw new IllegalArgumentException("RailTicketingProcessor class");
    }

    
    public static void run(String inputFilePath, String outputFilePath){
        Root jsonInput = Utils.parseJSONFile(inputFilePath);
    }
}
