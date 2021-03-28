package org.example;

import java.io.IOException;

public class RailTicketingProcessor {

    private RailTicketingProcessor(){
        throw new IllegalArgumentException("RailTicketingProcessor class");
    }
    public static void run(String inputFilePath, String outputFilePath) throws IOException{
        Root jsonInput = Utils.parseJSONFile(inputFilePath);
    }
}
