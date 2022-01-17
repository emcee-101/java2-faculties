package de.fherfurt.java1service3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacultyTest {

    @Test
    void getDecan() {
        // given
        String[] myString = {"netze", "gwp"};
        Faculty test = new Faculty("dekan", "ai", myString);

        // when
        String result = test.getDecan();

        // then
        Assertions.assertSame("dekan", result);
    }
}