package com.blogspot.przybyszd.spock.bean;

import com.blogspot.przybyszd.spock.dto.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonValidator {
    public void validatePerson(Person person) {
        String firstName = person.getFirstName();
        if (firstName == null || firstName.length() == 0) {
            throw new PersonValidationException("First name must be given");
        }

        String lastName = person.getLastName();
        if (lastName == null || lastName.length() == 0) {
            throw new PersonValidationException("Last name must be given");
        }

        Integer age = person.getAge();
        if (age != null && age < 0) {
            throw new PersonValidationException("Age cannot be negative");
        }
    }
}

