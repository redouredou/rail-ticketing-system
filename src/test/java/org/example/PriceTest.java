package org.example;

import org.example.outputmodel.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PriceTest {

    @Test
    @DisplayName("it should convert euros to cents")
    void given_Price_whenConvertEuroToCents_ThenReturnInCents(){
        //GIVEN
        Price price = new Price(2.60);
        int expectedCents = 260;
        //WHEN
        int cents = price.convertEuroToCents();
        //THEN
        assertThat(cents).isEqualTo(expectedCents);

    }

    @Test
    @DisplayName("it should thrown IllegalArgumentException when Price value is negative")
    void given_Price_whenInstanciation_ThenThrowIllegalArgumentException(){

        assertThatThrownBy(() -> {
           Price price = new Price(-2.30);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The price can't be negative");

    }
}
