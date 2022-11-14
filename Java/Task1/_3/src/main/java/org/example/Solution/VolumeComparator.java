package org.example.Solution;

import java.util.Comparator;

public class VolumeComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape a, Shape b) {
        return Double.compare(a.getVolume(), b.getVolume());
    }
}