package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @org.junit.jupiter.api.Test
    void positiveAndSorted() {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        Collection<Integer> result = Solution.positiveAndSorted(collection);

        assertIterableEquals(Arrays.asList(4, 3, 2, 1), result);
    }

    @org.junit.jupiter.api.Test
    void positiveAndNegativePassed() {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, -2, 3, -4));

        Collection<Integer> result = Solution.positiveAndSorted(collection);

        assertIterableEquals(Arrays.asList(3, 1), result);
    }

    @org.junit.jupiter.api.Test
    void allNegativePassed() {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(-1, -2, -3, -4));

        Collection<Integer> result = Solution.positiveAndSorted(collection);

        assertIterableEquals(Collections.emptyList(), result);
    }

    @org.junit.jupiter.api.Test
    void emptyCollectionPassed() {
        Collection<Integer> collection = new ArrayList<>();

        Collection<Integer> result = Solution.positiveAndSorted(collection);

        assertIterableEquals(Collections.emptyList(), result);
    }

    @org.junit.jupiter.api.Test
    void nullPassed() {
        Collection<Integer> collection = null;

        try {
            Collection<Integer> result = Solution.positiveAndSorted(collection);
            fail("NullPointerException in not implemented yet");
        }
        catch(NullPointerException e) {
            assertEquals("The collection argument cannot be null", e.getMessage());
        }
    }
}