package org.example;

import org.example.Solution.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>(Arrays.asList(new Cube(4), new Cube(1),
                new Cylinder(2, 5), new Sphere(4)));

        System.out.println(Sorter.sortedByVolume(shapes));
    }
}