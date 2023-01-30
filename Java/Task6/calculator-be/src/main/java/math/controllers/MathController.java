package math.controllers;

import math.services.ExampleGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/math")
@CrossOrigin
public class MathController {

    @Autowired
    ExampleGeneratorService exampleGeneratorService;

    @GetMapping("/examples")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public List<String> getExamples (@PathParam("count") int count) {
        return exampleGeneratorService.generate(count);
    }
}
