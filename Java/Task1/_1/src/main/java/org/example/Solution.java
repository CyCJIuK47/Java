package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Solution {

    public static Collection<Integer> positiveAndSorted(Collection<Integer> collection) throws NullPointerException {
        if (collection == null) {
            throw new NullPointerException("The collection argument cannot be null");
        }
        Collection<Integer> result = collection.stream()
                .filter(x-> x >= 0)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
        return result;
    }
}
