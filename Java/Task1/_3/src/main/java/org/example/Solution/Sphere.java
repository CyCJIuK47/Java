package org.example.Solution;

import java.util.Objects;

public class Sphere implements Shape{

    private double _radius;

    public Sphere(double radius) throws IllegalArgumentException {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius argument must be positive");
        }
        _radius = radius;
    }

    @Override
    public double getVolume() {
        return 4 / 3 * Math.PI * Math.pow(_radius, 3);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_radius=" + _radius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(sphere._radius, _radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_radius);
    }
}
