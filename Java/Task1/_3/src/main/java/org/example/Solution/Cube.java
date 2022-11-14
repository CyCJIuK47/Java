package org.example.Solution;

import java.util.Objects;

public class Cube implements  Shape{

    private double _side;

    public Cube(double side) throws IllegalArgumentException{
        if (side < 0) {
            throw new IllegalArgumentException("Side argument must be positive");
        }
        _side = side;
    }

    @Override
    public double getVolume(){
        return Math.pow(_side, 3);
    }

    @Override
    public String toString() {
        return "Cube{" +
                "_side=" + _side +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return Double.compare(cube._side, _side) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_side);
    }
}