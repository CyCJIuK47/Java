package org.example;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ParseException, URISyntaxException, TypeNotSupportedException {
        URL fileURL = Main.class.getClassLoader().getResource("validProperties.txt");
        var classObject = ClassPropertyLoader.loadFromProperties(TestClass.class,
                Path.of(fileURL.toURI()));
    }
}