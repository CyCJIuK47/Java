package org.example.Solution;

import java.util.Collection;
import java.util.List;

public class Sorter {

    public static List<Shape> sortedByVolume(Collection<Shape> collection) throws NullPointerException {
        if (collection == null) {
            throw new NullPointerException("The collection argument cannot be null");
        }
        return collection.stream()
                .sorted(new VolumeComparator())
                .toList();
    }
}
