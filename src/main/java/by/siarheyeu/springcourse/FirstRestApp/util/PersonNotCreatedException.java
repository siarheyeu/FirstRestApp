package by.siarheyeu.springcourse.FirstRestApp.util;

public class PersonNotCreatedException extends RuntimeException {
    public PersonNotCreatedException(String msg){
        super(msg);
    }
}
