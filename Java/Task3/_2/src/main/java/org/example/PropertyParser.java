package org.example;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;


public class PropertyParser {
    public static Map<String, String> parse(Path path) throws FileNotFoundException {
        Map<String, String> properties = new HashMap<>();

        try (Scanner scanner = new Scanner(path.toFile())) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Map.Entry<String, String> property = extractProperty(data);
                properties.put(property.getKey(), property.getValue());
            }
        }
        return properties;
    }

    private static Map.Entry<String, String> extractProperty(String property) {
        List<String> a = Arrays.stream(property.split("=")).map(String::trim).toList();
        return new AbstractMap.SimpleEntry<>(a.get(0), a.get(1));

    }
}
