package com.blogspot.przybyszd.spock.bean;

import com.blogspot.przybyszd.spock.dto.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonValidator {
    public void validatePerson(Person person) {
        validateNotBlank(person.getFirstName(), "First name must be given");
        validateNotBlank(person.getLastName(), "Last name must be given");
        validateAge(person.getAge());
    }

    private void validateAge(Integer age) {
        if (age == null){
            throw new PersonValidationException("Age must be given");
        }
        if( age < 0) {
            throw new PersonValidationException("Age cannot be negative");
        }
    }

    private void validateNotBlank(String firstName, String message) {
        if (firstName == null || firstName.length() == 0) {
            throw new PersonValidationException(message);
        }
    }
}

