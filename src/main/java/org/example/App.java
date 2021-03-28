package org.example;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws IOException {
        if(args.length >= 2){
            RailTicketingProcessor.run(args[0], args[1]);
        }else {
            throw new NullPointerException();
        }
    }
}
