package com.blogspot.przybyszd.spock.parameters03

import com.blogspot.przybyszd.spock.dto.Person
import groovy.sql.Sql
import spock.lang.Specification
import spock.lang.Unroll

class ParametersFromDbTest extends Specification {

    static Sql sql = Sql.newInstance('jdbc:h2:mem:', 'sa', '', 'org.h2.Driver')

    def setupSpec() {
        sql.execute('''DROP TABLE IF EXISTS person;
                        CREATE TABLE person (
                          first_name VARCHAR(256) NOT NULL,
                          last_name  VARCHAR(256) NOT NULL,
                          age        INT          NOT NULL
                        );''')
        sql.executeInsert('''INSERT INTO person (first_name, last_name, age) VALUES
                                ('Tom', 'Smith', 24),
                                ('Jan', 'Kowalski', 30);''')
    }

    def cleanupSpec() {
        sql.close()
    }

    @Unroll
    def 'should set person data with #lastName, #firstName and #age'() {
        when:
            Person person = new Person(lastName: lastName, firstName: firstName, age: age)
        then:
            person.firstName == firstName
            person.lastName == lastName
            person.age == age
        where:
            [firstName, lastName, age] << sql.rows('SELECT * FROM person;')
    }

    @Unroll
    def 'should set person data with #lastName, #firstName and #age 2'() {
        when:
            Person person = new Person(lastName: lastName, firstName: firstName, age: age)
        then:
            person.firstName == firstName
            person.lastName == lastName
            person.age == age
        where:
            [firstName, lastName, age] << sql.rows('SELECT first_name, last_name, age FROM person;')
    }

    @Unroll
    def 'should set person data with #lastName and #firstName'() {
        when:
            Person person = new Person(lastName: lastName, firstName: firstName)
        then:
            person.firstName == firstName
            person.lastName == lastName
        where:
            [firstName, lastName] << sql.rows('SELECT * FROM person;')
    }

    @Unroll
    def 'should set person data with #lastName and #age'() {
        when:
            Person person = new Person(lastName: lastName, age: age)
        then:
            person.lastName == lastName
            person.age == age
        where:
            [_, lastName, age] << sql.rows('SELECT * FROM person;')
    }

}
