package com.blogspot.przybyszd.spock.annotations08

import com.blogspot.przybyszd.spock.dto.Person
import spock.lang.Specification
import spock.lang.Subject

@Subject(Person)
class SubjectTest extends Specification {

    @Subject
    Person person = new Person('John', 'Smith', 21)

    def 'should be adult'() {
        expect:
            person.isAdult()
    }
}
