package bookstore.test.helpers.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ResponseParser {

    public static <T>T parseResponse(ObjectMapper objectMapper, MvcResult mvcResult, Class<T> c) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), c);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }

    public static <T> List<T> parseListResponse(ObjectMapper objectMapper, MvcResult mvcResult, Class<T> c) {
        try {
            CollectionType type = objectMapper.getTypeFactory()
                    .constructCollectionType(ArrayList.class, c);
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), type);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }
}
