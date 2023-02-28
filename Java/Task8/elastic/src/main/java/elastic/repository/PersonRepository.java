package elastic.repository;

import elastic.model.Person;
import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<Person, String>, CustomPersonRepository {

}
