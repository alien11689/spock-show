package com.blogspot.przybyszd.spock.exception04

import com.blogspot.przybyszd.spock.bean.PersonValidationException
import com.blogspot.przybyszd.spock.bean.PersonValidator
import com.blogspot.przybyszd.spock.dto.Person
import spock.lang.Specification
import spock.lang.Unroll

class PersonValidatorTest extends Specification {

    PersonValidator sut = new PersonValidator()

    def "should pass validation"() {
        given:
            Person person = new Person(firstName: "Tom", lastName: "Smith", age: 30)
        when:
            sut.validatePerson(person)
        then:
            notThrown(PersonValidationException)
    }

    @Unroll
    def "should not pass validation"() {
        given:
            Person person = new Person(firstName: firstName, lastName: lastName, age: age)
        when:
            sut.validatePerson(person)
        then:
            PersonValidationException exception = thrown(PersonValidationException)
            exception.message == message
        where:
            firstName | lastName   | age | message
            "Tom"     | "Smith"    | -1  | "Age cannot be negative"
            null      | "Kowalski" | 19  | "First name must be given"
            ""        | "Kowalski" | 19  | "First name must be given"
            "Jan"     | null       | 19  | "Last name must be given"
            "Jan"     | ""         | 19  | "Last name must be given"


    }
}
