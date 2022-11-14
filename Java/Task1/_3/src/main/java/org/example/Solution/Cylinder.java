package org.example.Solution;

import java.util.Objects;

public class Cylinder implements Shape{

    private double _radius;
    private double _height;

    public Cylinder(double radius, double height) throws IllegalArgumentException {
        if (radius < 0 || height < 0) {
            throw new IllegalArgumentException("Both arguments must be positive");
        }
        _radius = radius;
        _height = height;
    }

    @Override
    public double getVolume() {
        return Math.PI * Math.pow(_radius, 2) * _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_radius=" + _radius +
                ", _height=" + _height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cylinder cylinder = (Cylinder) o;
        return Double.compare(cylinder._radius, _radius) == 0 && Double.compare(cylinder._height, _height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_radius, _height);
    }
}
