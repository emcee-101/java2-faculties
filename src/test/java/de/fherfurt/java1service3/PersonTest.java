package de.fherfurt.java1service3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void getName() {
        // given
        String[] myString = {"netze", "gwp"};
        Faculty test = new Faculty("name", "ai", myString);

        // when
        String result = test.getName();


        // then
        Assertions.assertSame("name", result);
    }
}