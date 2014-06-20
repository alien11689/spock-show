package com.blogspot.przybyszd.spock.helperMethod07

import com.blogspot.przybyszd.spock.dto.Person
import spock.lang.Specification

class HelperMethodTest extends Specification {

    def "should check person"() {
        when:
            Person result = new Person("Tom", "Smith", 20)
        then:
            result != null
            result.firstName == "Tom"
            result.lastName == "Smith"
            result.age == 20
    }

    def "should check person with boolean helper method"() {
        when:
            Person result = new Person("Tom", "Smith", 20)
        then:
            checkPerson(result, "Tom", "Smith", 20)
    }

    def "should check person with assert helper method"() {
        when:
            Person result = new Person("Tom", "Smith", 20)
        then:
            checkPersonWithAssert(result, "Tom", "Smith", 20)
    }

    boolean checkPerson(Person person, String firstName, String lastName, int age) {
        person != null &&
                person.firstName == firstName &&
                person.lastName == lastName &&
                person.age == age
    }


    void checkPersonWithAssert(Person person, String firstName, String lastName, int age) {
        assert person != null
        assert person.firstName == firstName
        assert person.lastName == lastName
        assert person.age == age
    }
}
