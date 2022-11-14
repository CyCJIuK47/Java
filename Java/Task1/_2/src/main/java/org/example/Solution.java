package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {

    /**
     *
     * @param text string with hashtags
     * @return List of unique hashtags from text
     */
    private static List<String> collectHashTagsFrom(String text) {
        String[] hashTags = text.split("[ ,.;!?:-]");
        List<String> result = Arrays.stream(hashTags)
                .filter(x -> x.startsWith("#") && x.length() > 1)
                .distinct()
                .collect(Collectors.toList());
        return result;
    }

    private static List<String> concatenate(List<List<String>> lists) {
        List<String> result = new ArrayList<>();
        lists.forEach(result::addAll);
        return result;
    }

    public static Map<String, Long> top5HashTags(List<String> texts) throws NullPointerException {
        if (texts == null) {
            throw new NullPointerException("The texts argument cannot be null");
        }

        List<List<String>> hashtags = texts.stream()
                .map(x -> collectHashTagsFrom(x))
                .toList();

        List<String> joinedHashTags = concatenate(hashtags);

        Map<String, Long> counts = joinedHashTags.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> result = counts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return result;
    }
}
