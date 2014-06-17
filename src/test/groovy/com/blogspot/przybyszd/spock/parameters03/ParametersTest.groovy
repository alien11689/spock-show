package com.blogspot.przybyszd.spock.parameters03

import com.blogspot.przybyszd.spock.dto.Person
import spock.lang.Specification
import spock.lang.Unroll


class ParametersTest extends Specification {

    @Unroll
    def "should set person data"() {
        when:
            Person person = new Person(lastName: lastName, firstName: firstName, age: age)
        then:
            person.firstName == firstName
            person.lastName == lastName
            person.age == age
        where:
            lastName   | firstName | age
            "Smith"    | "John"    | 25
            "Kowalski" | "Jan"     | 24
    }

    @Unroll
    def "should set person with #lastName, #firstName and #age"() {
        when:
            Person person = new Person(lastName: lastName, firstName: firstName, age: age)
        then:
            person.firstName == firstName
            person.lastName == lastName
            person.age == age
        where:
            lastName   | firstName | age
            "Smith"    | "John"    | 25
            "Kowalski" | "Jan"     | 24
    }

    @Unroll("should set person with #lastName, #firstName and #age")
    def "should set person with lastName, firstName and age"() {
        when:
            Person person = new Person(lastName: lastName, firstName: firstName, age: age)
        then:
            person.firstName == firstName
            person.lastName == lastName
            person.age == age
        where:
            lastName   | firstName | age
            "Smith"    | "John"    | 25
            "Kowalski" | "Jan"     | 24
    }

    @Unroll
    def "should check if person is adult with table"() {
        expect:
            new Person(age: age).isAdult() == adult
        where:
            age || adult
            17  || false
            18  || true
            19  || true
    }

    @Unroll
    def "should check if person is adult with list"() {
        expect:
            new Person(age: age).isAdult() == adult
            ageSquare == age * age
        where:
            age << [17, 18, 19]
            adult << [false, true, true]
            ageSquare = age * age
    }

    @Unroll
    def "should set first name"() {
        when:
            Person person = new Person(firstName: firstName)
        then:
            person.firstName == firstName
        where:
            firstName | _
            "John"    | _
            "Jan"     | _
    }
}
