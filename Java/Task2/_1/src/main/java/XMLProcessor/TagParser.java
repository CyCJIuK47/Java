package XMLProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagParser {

    public static String parse(String tag) {
        Pattern tagPattern = Pattern.compile("\\s*?<(\\S+)\\s?([\\s\\S]*?)/>\\s*?");
        Pattern allAttributes = Pattern.compile("\\s*([\\w: \\-]+)(\\s*=\\s*(\"(.*?)\"|'(.*?)'|([^ ]*))|(\\s+|\\z))\\s*");

        Matcher m = tagPattern.matcher(tag);
        boolean tagFound = m.find();

        String attributes = m.group(2);
        m = allAttributes.matcher(attributes);

        Map<String, String> attrs = new HashMap<>();
        Map<String, String> attrsStrings = new HashMap<>();

        while (m.find()) {
           String attribute = m.group(0);
           Map.Entry<String, String> parsedAttr = AttributeParser.parse(attribute);

           String processedTag = tag;
           attrs.put(parsedAttr.getKey(), parsedAttr.getValue());
           attrsStrings.put(parsedAttr.getKey(), attribute);
        }

        //delete surname
        String newTag = tag;
        newTag = newTag.replace(attrsStrings.get("surname"), "");

        //add surname to name
        newTag = newTag.replace(attrs.get("name"), attrs.get("name") + " " + attrs.get("surname"));
        return newTag;
    }
}
