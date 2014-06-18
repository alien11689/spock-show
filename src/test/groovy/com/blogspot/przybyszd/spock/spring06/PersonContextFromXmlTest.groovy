package com.blogspot.przybyszd.spock.spring06

import com.blogspot.przybyszd.spock.bean.PersonController
import com.blogspot.przybyszd.spock.bean.PersonDao
import com.blogspot.przybyszd.spock.bean.PersonValidationException
import com.blogspot.przybyszd.spock.dto.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(locations = "classpath:personContext.xml")
class PersonContextFromXmlTest extends Specification {
    @Autowired
    PersonController personController

    @Autowired
    PersonDao personDao

    @Unroll
    def "should save person and find it"() {
        given:
            Person person = new Person("Tom", "Smith", 25)
        when:
            personController.addPerson(person)
        then:
            personDao.findByLastName("Smith") == [person]
    }

    @Unroll
    def "should not save person because of validation"() {
        given:
            Person person = new Person("Tom", "Smith", -20)
        when:
            personController.addPerson(person)
        then:
            thrown(PersonValidationException)
    }
}
