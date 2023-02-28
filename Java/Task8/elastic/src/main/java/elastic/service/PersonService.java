package elastic.service;

import elastic.model.NameCount;
import elastic.model.Person;
import elastic.model.PersonPartialInfo;
import elastic.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public List<PersonPartialInfo> findByFullName(String fullName) {
        return personRepository.findByFullName(fullName);
    }

    public List<NameCount> getTopTenPopularPepNames() {
        return personRepository.getTopTenPopularPepNames();
    }

}
