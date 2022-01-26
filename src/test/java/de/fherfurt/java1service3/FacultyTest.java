package de.fherfurt.java1service3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacultyTest {

    @Test
    void getDecan() {
        // given
        Faculty test = new Faculty("dekan", "ai", "testUni");

        // when
        String result = test.getUniversityName();

        // then
        Assertions.assertSame("testUni", result);
    }
}