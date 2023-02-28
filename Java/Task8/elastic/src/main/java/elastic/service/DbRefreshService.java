package elastic.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import elastic.model.Person;
import elastic.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

@Service
public class DbRefreshService {

    private static final int BATCH_SIZE = 2048;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


    public void refreshWithZip(MultipartFile zipFile) throws IOException {

        personRepository.deleteAll();

        try(ZipInputStream zipInputStream = new ZipInputStream(zipFile.getInputStream())) {

            zipInputStream.getNextEntry();
            ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());

            try(JsonParser jsonParser = objectMapper.getFactory().createParser(zipInputStream)) {

                if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
                    throw new IllegalStateException("Expected content to be an array");
                }

                while (jsonParser.nextToken() != JsonToken.END_ARRAY && jsonParser.currentToken() != null) {

                    List<Person> persons = new ArrayList<>(BATCH_SIZE);
                    for (int i = 0; i < BATCH_SIZE && jsonParser.nextToken() != JsonToken.END_ARRAY; i++) {
                        Person person = objectMapper.readValue(jsonParser, Person.class);
                        persons.add(person);
                    }

                    elasticsearchOperations.save(persons, elasticsearchOperations.getIndexCoordinatesFor(Person.class));
                }
            }
        }
    }
}
