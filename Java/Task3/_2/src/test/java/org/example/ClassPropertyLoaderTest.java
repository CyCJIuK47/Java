package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

import static org.example.ClassPropertyLoader.loadFromProperties;


class ClassPropertyLoaderTest {

    @Test
    void loadFromValidPropertiesTest() throws URISyntaxException, IOException, ParseException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, TypeNotSupportedException {
        URL file = getClass().getClassLoader().getResource("validProperties.txt");

        TestClass classObject = loadFromProperties(TestClass.class, Path.of(file.toURI()));
        TestClass real = new TestClass(1,
                2,
                "1",
                "abc",
                new SimpleDateFormat("dd.MM.yyyy hh:mm").parse("29.11.2022 18:30").toInstant());

        assertEquals(classObject, real);
    }

    @Test
    void loadFromNotValidPropertiesTest() {
        URL fileURL = getClass().getClassLoader().getResource("notValidProperties.txt");

        assertThrows(NumberFormatException.class,
                ()->{
                    loadFromProperties(TestClass.class, Path.of(fileURL.toURI()));
                });

    }

    @Test
    void loadFromBadDateFormatPropertiesTest() {
        URL fileURL = getClass().getClassLoader().getResource("badDateFormatProperties.txt");

        assertThrows(ParseException.class,
                ()->{
                    loadFromProperties(TestClass.class, Path.of(fileURL.toURI()));
                });
    }

    @Test
    void NoSuchPropertiesTest() {
        URL fileURL = getClass().getClassLoader().getResource("noSuchProperties.txt");

        assertThrows(NoSuchFieldException.class,
                ()->{
                    loadFromProperties(TestClass.class, Path.of(fileURL.toURI()));
                });
    }

    @Test
    void typeNotSupportedTest() {
        URL fileURL = getClass().getClassLoader().getResource("typeNotSupportedProperties.txt");

        assertThrows(TypeNotSupportedException.class,
                ()->{
                    loadFromProperties(NotSupportedTypeClass.class, Path.of(fileURL.toURI()));
                });
    }
}