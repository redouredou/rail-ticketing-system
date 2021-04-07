package org.example;

import org.example.error.RailTicketingArgumentsNullException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{

    private static final  Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main( String[] args ) throws RailTicketingArgumentsNullException {

        LOGGER.info("Welcome to the rail ticketing app! ");
        LOGGER.info("Arguments checking ...");
        if(args.length == 0){
            throw new RailTicketingArgumentsNullException("Input data path and output path are missing");
        }
        LOGGER.log(Level.FINE, () -> "Input data path : " + args[0]);
        if(args.length == 1){
            throw new RailTicketingArgumentsNullException("Output path is missing");
        }
        LOGGER.log(Level.FINE, () -> "Output generation path : " + args[1]);
        LOGGER.info("Running the rail ticketing processing ... ");
        RailTicketingProcessor.run(args[0], args[1]);
        LOGGER.info("Done ! Look at your output file");
    }
}
