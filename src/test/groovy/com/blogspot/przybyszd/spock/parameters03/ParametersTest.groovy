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
    def "should set person data 2"(String firstName, String lastName, int age) {
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

    @Unroll("should set person with #lastName.length(), #firstName.toUpperCase() and #age")
    def "should set person with lastName, firstName and age 2"() {
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

    @Unroll("should set person with #lastName.length(), #firstName.toUpperCase() and #age when last name starts with #firstLetter")
    def "should set person with lastName, firstName and age 3"() {
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

            firstLetter = lastName.charAt(0)
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
    def "should check if person with age #person.age is adult => #adult"() {
        expect:
            person.isAdult() == adult
        where:
            person              || adult
            new Person(age: 17) || false
            new Person(age: 18) || true
            new Person(age: 19) || true
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
    def "should check if person is adult with list 2"() {
        expect:
            new Person(age: age).isAdult() == adult
        where:
            [age, adult] << [[17,false], [18,true], [19, true]]
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
