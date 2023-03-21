package by.siarheyeu.springcourse.FirstRestApp.controllers;

import by.siarheyeu.springcourse.FirstRestApp.models.Person;
import by.siarheyeu.springcourse.FirstRestApp.services.PeopleService;
import by.siarheyeu.springcourse.FirstRestApp.util.PersonErrorResponse;
import by.siarheyeu.springcourse.FirstRestApp.util.PersonNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id){
        return peopleService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
        }
        peopleService.save(person);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasn't found!",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
