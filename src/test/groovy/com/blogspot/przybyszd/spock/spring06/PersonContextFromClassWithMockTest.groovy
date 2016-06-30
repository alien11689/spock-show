package com.blogspot.przybyszd.spock.spring06

import com.blogspot.przybyszd.spock.bean.PersonDao
import com.blogspot.przybyszd.spock.dto.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = TestPersonContextConfiguration)
class PersonContextFromClassWithMockTest extends Specification {
    @Autowired
    PersonDao personDao

    @Unroll
    def 'should save person and find it'() {
        given:
            Person person = new Person('Tom', 'Smith', 25)
            personDao.findByLastName('Smith') >> [person]
        expect:
            personDao.findByLastName('Smith') == [person]
    }
}
