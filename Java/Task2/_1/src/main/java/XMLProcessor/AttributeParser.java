package XMLProcessor;

import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeParser {


    public static Entry<String, String> parse(String attribute) {
        Pattern attributePattern =
                Pattern.compile("\\s*(\\S+)\\s*=\\s*[\'\"]([^']*)[\'\"]\\s*");


        Matcher m = attributePattern.matcher(attribute);
        if (m.matches()) {
            String key = m.group(1);
            String value = m.group(2);

            return new AbstractMap.SimpleEntry<>(key, value);
        }

        throw new RuntimeException("Cant match attribute");
    }
}
