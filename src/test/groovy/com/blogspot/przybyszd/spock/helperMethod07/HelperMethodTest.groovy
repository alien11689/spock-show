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

    def "should check person with helper method"() {
        when:
            Person result = new Person("Tom", "Smith", 20)
        then:
            checkPerson(result, "Tom", "Smith", 20)
    }

    void checkPerson(Person person, String firstName, String lastName, int age) {
        assert person != null
        assert person.firstName == firstName
        assert person.lastName == lastName
        assert person.age == age
    }
}
