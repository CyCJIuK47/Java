package elastic.controller;

import elastic.model.NameCount;
import elastic.model.PersonPartialInfo;
import elastic.service.DbRefreshService;
import elastic.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PersonController {

    @Autowired
    private DbRefreshService dbRefreshService;

    @Autowired
    private PersonService personService;


    @PostMapping("/upload")
    public void refreshDbWithZip(@RequestParam("file") MultipartFile file) throws IOException {
        dbRefreshService.refreshWithZip(file);
    }

    @GetMapping("/find-by-fullname")
    public List<PersonPartialInfo> findByFullName(@RequestParam("fullName") String fullName) {
        return personService.findByFullName(fullName);
    }

    @GetMapping("/get-top-ten-popular-names")
    public List<NameCount> getTopTenPopularNames() {
        return personService.getTopTenPopularPepNames();
    }
}

