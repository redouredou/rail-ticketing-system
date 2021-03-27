package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    void the_Method_Main_should_throw_NullPointerException_when_output_argument_is_missing()
    {

        //GIVEN
        String[] args = new String[2];
        args[0] = "path/to/fileInput.txt";

        //WHEN
        //THEN
        Assertions.assertThrows(NullPointerException.class, () -> App.main(args));

    }

    @Test
    void the_Method_Main_should_throw_NullPointerException_when_input_argument_is_missing()
    {

        //GIVEN
        String[] args = new String[2];
        args[1] = "path/to/fileOutput.txt";

        //WHEN
        //THEN
        Assertions.assertThrows(NullPointerException.class, () -> App.main(args));

    }

    @Test
    void the_Method_Main_should_throw_NullPointerException_when_args_is_empty()
    {
        //GIVEN
        String[] args = {};

        //WHEN
        //THEN
        Assertions.assertThrows(NullPointerException.class, () -> App.main(args));
    }


}
