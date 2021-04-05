package org.example;


import org.example.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class UtilsTest {

    @Test
    void given_List_whenPartitionByPair_ThenReturnGetPartitionOfThatListWithTwoItems(){
        //GIVEN
        List inputList = Arrays.asList(1,2,3,6,5,2);
        List<List> expectedList = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(3,6),
                Arrays.asList(5,2));
        //WHEN
        List<List> actualList = Utils.partitionByPair(inputList);

        //THEN
        Assertions.assertEquals(expectedList, actualList);
    }
}
