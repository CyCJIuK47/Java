package org.example;

import java.time.Instant;
import java.util.Objects;

public class TestClass {
    @Property(name = "testInt")
    public int testInt;

    @Property(name = "testInteger")
    private Integer testInt1;

    @Property(name = "someString")
    private String testString;

    public String testStringWithoutProperty;

    @Format("dd.MM.yyyy hh:mm")
    public  Instant date;

    public TestClass() {

    }

    public TestClass(int testInt, Integer testInt1, String testString, String testStringWithoutProperty, Instant date) {
        this.testInt = testInt;
        this.testInt1 = testInt1;
        this.testString = testString;
        this.testStringWithoutProperty = testStringWithoutProperty;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass testClass = (TestClass) o;
        return testInt == testClass.testInt && Objects.equals(testInt1, testClass.testInt1) && Objects.equals(testString, testClass.testString) && Objects.equals(testStringWithoutProperty, testClass.testStringWithoutProperty) && Objects.equals(date, testClass.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testInt, testInt1, testString, testStringWithoutProperty, date);
    }
}
