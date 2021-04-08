package org.example;

import org.example.error.RailTicketingArgumentsNullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest
{

    @Test
    void it_should_RailTicketingArgumentsNullException_with_message_when_args_is_empty()
    {
        //GIVEN
        String[] args = {};

        //WHEN
        //THEN

        Exception exception = Assertions.assertThrows(RailTicketingArgumentsNullException.class, () -> App.main(args));

        Assertions.assertEquals("Input data path and output path are missing", exception.getMessage());
    }

    @Test
    void it_should_RailTicketingArgumentsNullException_with_message_when_output_argument_is_missing()
    {

        //GIVEN
        String[] args = new String[1];
        args[0] = "path/to/fileInput.txt";

        //WHEN
        //THEN
        Exception exception = Assertions.assertThrows(RailTicketingArgumentsNullException.class, () -> App.main(args));

        Assertions.assertEquals("Output path is missing", exception.getMessage());
    }

}