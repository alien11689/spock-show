package com.blogspot.przybyszd.spock.extra09

import com.blogspot.przybyszd.spock.dto.Person
import spock.lang.Specification
import java.lang.Void as Should


class ShouldReplacementTest extends Specification {

    void "should set first name from constructor"() {
        when:
            Person person = new Person(firstName: "Bob")
        then:
            person.firstName == "Bob"
    }

    Should "set first name from constructor"() {
        when:
            Person person = new Person(firstName: "Bob")
        then:
            person.firstName == "Bob"
    }

}
