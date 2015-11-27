package com.blogspot.przybyszd.spock.bean

import com.blogspot.przybyszd.spock.dto.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PersonDao {
    private final JdbcTemplate jdbcTemplate

    @Autowired
    PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }

    @Transactional
    void persist(Person person) {
        jdbcTemplate.execute("Insert into person (first_name, last_name, age) values ('${person.firstName}', '${person.lastName}', ${person.age})")
    }

    @Transactional
    void persist(List<Person> persons) {
        persons.each {
            persist(it)
        }
    }

    List<Person> findByLastName(String lastName) {
        jdbcTemplate.queryForList('select first_name, last_name, age from person where last_name = ?', [lastName] as Object[]).collect {
            Map row -> new Person(row.first_name, row.last_name, row.age)
        }
    }

    void close() {
        println 'Closing person dao'
    }
}
