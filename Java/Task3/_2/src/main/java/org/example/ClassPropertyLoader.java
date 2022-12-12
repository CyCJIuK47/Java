package org.example;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class ClassPropertyLoader {

    public static <T> T loadFromProperties(Class<T> cls, Path propertiesPath) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException, ParseException, TypeNotSupportedException {
        Map<String, String> properties = PropertyParser.parse(propertiesPath);

        List<Field> classProperties = Arrays.stream(cls.getDeclaredFields()).toList();

        Constructor<T> classConstructor = cls.getConstructor();
        T classObj = classConstructor.newInstance();

        List<String> fieldsNames = classProperties.stream()
                .map(x -> {
                    Property a = x.getAnnotation(Property.class);
                    return a != null ? a.name() : x.getName();
                }).toList();

        Map<String, Field> propertiesToFields = IntStream.range(0, classProperties.size())
                .boxed()
                .collect(toMap(fieldsNames::get, classProperties::get));

        for (var entry : properties.entrySet()) {
            Field field = propertiesToFields.get(entry.getKey());
            if (field == null)
                throw new NoSuchFieldException("Field " +
                        entry.getValue() + "doesn`t match with either field name or the annotation");
            setProperty(classObj, field, entry.getValue());
        }

        return classObj;
    }

    private static <T> void setProperty(T obj, Field propertyField, String propertyValue) throws IllegalAccessException, ParseException, TypeNotSupportedException {

        if (propertyField == null)
            throw new NullPointerException();

        propertyField.setAccessible(true);

        Class<?> fieldType = propertyField.getType();

        if (fieldType == int.class || fieldType == Integer.class) {
            int intValue = Integer.parseInt(propertyValue);
            propertyField.set(obj, intValue);
            return;
        }

        if (fieldType == String.class) {
            propertyField.set(obj, propertyValue);
            return;
        }

        if (fieldType == Instant.class) {
            Format format = propertyField.getAnnotation(Format.class);
            Date date;
            if (format == null)
                date = new SimpleDateFormat().parse(propertyValue);
            else
                date = new SimpleDateFormat(format.value()).parse(propertyValue);

            propertyField.set(obj, date.toInstant());
            return;
        }

        throw new TypeNotSupportedException(fieldType.getName() + "not supported.");
    }

}
