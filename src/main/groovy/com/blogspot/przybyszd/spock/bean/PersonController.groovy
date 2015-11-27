package com.blogspot.przybyszd.spock.bean

import com.blogspot.przybyszd.spock.dto.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PersonController {
    private final PersonValidator personValidator
    private final PersonDao personDao

    @Autowired
    PersonController(PersonValidator personValidator, PersonDao personDao) {
        this.personValidator = personValidator
        this.personDao = personDao
    }

    void addPerson(Person person) {
        personValidator.validatePerson(person)
        personDao.persist(person)
    }
}
