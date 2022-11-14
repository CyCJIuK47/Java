package org.example;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @org.junit.jupiter.api.Test
    void top5HashTags() {
        List<String> texts = new ArrayList<>(List.of(
                "#a #b! #c - #d #e #q #w #t #y #u",
                "#1 2 #3 #5 #5 #6???",
                "#1 2 #3 #5 #5 #6...",
                "#1 2 #3, #4 #5 #6 7!",
                "#1 #2 #3; #4 #5: #7"));

        Map<String, Long> top5HashTags = Solution.top5HashTags(texts);

        TreeMap <String, Long> expected = new TreeMap<>();
        expected.putAll(Map.of(
                "#3", Long.valueOf(4),
                "#4", Long.valueOf(2),
                "#5", Long.valueOf(4),
                "#6", Long.valueOf(3),
                "#1",Long.valueOf(4)));

        assertEquals(expected, new TreeMap<>(top5HashTags));
    }

    @org.junit.jupiter.api.Test
    void emptyCollectionPassed() {
        List<String> texts = Collections.emptyList();

        Map<String, Long> top5HashTags = Solution.top5HashTags(texts);

        assertEquals(new TreeMap<String, Long>(), top5HashTags);
    }

    @org.junit.jupiter.api.Test
    void NullPassed() {
        List<String> texts = null;

        try {
            Map<String, Long> top5HashTags = Solution.top5HashTags(texts);
            fail("NullPointerException in not implemented yet");
        }
        catch(NullPointerException e) {
            assertEquals("The texts argument cannot be null", e.getMessage());
        }
    }
}