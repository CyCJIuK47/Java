package elastic.utils.sourcefilters;

import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;

import java.lang.reflect.Field;
import java.util.Arrays;


public class ClassToSourceFilterConverter {

    public static FetchSourceFilter convert(Class fromClass) {

        String[] filterFields = Arrays
                .stream(fromClass.getFields())
                .map(Field::getName)
                .toArray(String[]::new);

        return new FetchSourceFilter(filterFields, new String[]{});
    }

}
