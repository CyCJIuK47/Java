package org.example.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

    @org.junit.jupiter.api.Test
    void sortedByVolume() {
        List<Shape> shapes = new ArrayList<>(Arrays.asList(new Cube(4), new Cube(1),
                new Cylinder(2, 5), new Sphere(4)));

        List<Shape> sortedShapes = Sorter.sortedByVolume(shapes);

        assertIterableEquals(new ArrayList<>(Arrays.asList(new Cube(1),
                new Cylinder(2, 5), new Cube(4), new Sphere(4))), sortedShapes);
    }

    @org.junit.jupiter.api.Test
    void emptyCollectionPassed() {
        List<Shape> shapes = new ArrayList<>();

        List<Shape> sortedShapes = Sorter.sortedByVolume(shapes);

        assertIterableEquals(Collections.emptyList(), sortedShapes);
    }

    @org.junit.jupiter.api.Test
    void nullPassed() {
        List<Shape> shapes = null;

        try {
            List<Shape> sortedShapes = Sorter.sortedByVolume(shapes);
            fail("NullPointerException in not implemented yet");
        }
        catch(NullPointerException e) {
            assertEquals("The collection argument cannot be null", e.getMessage());
        }
    }
}