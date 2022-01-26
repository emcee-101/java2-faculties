package de.fherfurt.java1service3;

import de.fherfurt.java1service3.enums.CourseType;
import de.fherfurt.java1service3.enums.ModuleCertificationType;
import de.fherfurt.java1service3.enums.ModuleType;

import java.util.*;

public class TestData {
    private final Module module = new Module(
            "Mathe",
            3,
            List.of("Mustermann"),
            ModuleType.ELECTIVE,
            "/docs/document",
            ModuleCertificationType.EXAM,
            "ai"
    );
    private final Course course = new Course(
            "ai",
            7,
            -1,
            CourseType.BACHELOR,
            "Mustermann",
            "Technik"
            );
    private final Faculty faculty = new Faculty(
            "Mustermann",
            "Technik",
            "FH-Erfurt"
    );
    University university = new University(
            "FH-Erfurt",
            "Mustermann"
    );
}
