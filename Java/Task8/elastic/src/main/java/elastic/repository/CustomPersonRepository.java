package elastic.repository;

import elastic.model.NameCount;
import elastic.model.PersonPartialInfo;

import java.util.List;


public interface CustomPersonRepository {

    List<PersonPartialInfo> findByFullName(String fullName);

    List<NameCount> getTopTenPopularPepNames();

}
