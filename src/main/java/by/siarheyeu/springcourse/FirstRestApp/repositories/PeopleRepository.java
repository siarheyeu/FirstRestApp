package by.siarheyeu.springcourse.FirstRestApp.repositories;

import by.siarheyeu.springcourse.FirstRestApp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}