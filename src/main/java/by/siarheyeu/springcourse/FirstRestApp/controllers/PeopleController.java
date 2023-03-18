package by.siarheyeu.springcourse.FirstRestApp.controllers;

import by.siarheyeu.springcourse.FirstRestApp.models.Person;
import by.siarheyeu.springcourse.FirstRestApp.services.PeopleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;


    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<Person> getPeople(){
        return peopleService.findAll();
    }
}
