package com.blogspot.przybyszd.spock.mockingandstubing05

import com.blogspot.przybyszd.spock.bean.PersonDao
import com.blogspot.przybyszd.spock.dto.Person
import org.springframework.dao.DataAccessException
import org.springframework.dao.DataRetrievalFailureException
import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.Specification

class PersonDaoTest extends Specification {

    JdbcTemplate jdbcTemplate = Mock(JdbcTemplate)

    PersonDao sut = new PersonDao(jdbcTemplate)

    def "should persist one person"() {
        given:
            Person person = new Person("John", "Smith", 20)
        when:
            sut.persist(person)
        then:
            1 * jdbcTemplate.execute("Insert into person (first_name, last_name, age) values ('John', 'Smith', 20)")
    }

    def "should persist many persons"() {
        given:
            List<Person> persons = [new Person("John", "Smith", 20), new Person("Jan", "Kowalski", 15)]
        when:
            sut.persist(persons)
        then:
            1 * jdbcTemplate.execute("Insert into person (first_name, last_name, age) values ('John', 'Smith', 20)")
            1 * jdbcTemplate.execute("Insert into person (first_name, last_name, age) values ('Jan', 'Kowalski', 15)")
    }

    def "should persist many persons 2"() {
        given:
            List<Person> persons = [new Person("John", "Smith", 20), new Person("Jan", "Kowalski", 15)]
        when:
            sut.persist(persons)
        then:
            2 * jdbcTemplate.execute(_)
    }

    def "should persist many persons 3"() {
        given:
            List<Person> persons = [new Person("John", "Smith", 20), new Person("Jan", "Kowalski", 15)]
        when:
            sut.persist(persons)
        then:
            (1..3) * jdbcTemplate.execute(_)
    }

    def "should persist many persons 4"() {
        given:
            List<Person> persons = [new Person("John", "Smith", 20), new Person("Jan", "Kowalski", 15)]
        when:
            sut.persist(persons)
        then:
            (1.._) * jdbcTemplate.execute(_)
    }

    def "should persist many persons 5"() {
        given:
            List<Person> persons = [new Person("John", "Smith", 20), new Person("Jan", "Kowalski", 15)]
        when:
            sut.persist(persons)
        then:
            _ * jdbcTemplate.execute(_)
    }

    def "should persist many persons 6"() {
        given:
            List<Person> persons = [new Person("John", "Smith", 20), new Person("Jan", "Kowalski", 15)]
        when:
            sut.persist(persons)
        then:
            2 * _.execute(_)
    }

    def "should persist many persons 7"() {
        given:
            List<Person> persons = [new Person("John", "Smith", 20), new Person("Jan", "Kowalski", 15)]
        when:
            sut.persist(persons)
        then:
            2 * jdbcTemplate._(_)
    }

    def "should persist many persons 8"() {
        given:
            List<Person> persons = [new Person("John", "Smith", 20), new Person("Jan", "Kowalski", 15)]
        when:
            sut.persist(persons)
        then:
            2 * jdbcTemplate./exe.*/(_)
    }

    def "should persist many persons 9"() {
        given:
            List<Person> persons = [new Person("John", "Smith", 20), new Person("Jan", "Kowalski", 15)]
        when:
            sut.persist(persons)
        then:
            2 * jdbcTemplate.execute({
                String sql -> sql.endsWith("('John', 'Smith', 20)") || sql.endsWith("('Jan', 'Kowalski', 15)")
            })
    }

    def "should persist many persons in order"() {
        given:
            List<Person> persons = [new Person("John", "Smith", 20), new Person("Jan", "Kowalski", 15)]
        when:
            sut.persist(persons)
        then:
            1 * jdbcTemplate.execute("Insert into person (first_name, last_name, age) values ('John', 'Smith', 20)")
        then:
            1 * jdbcTemplate.execute("Insert into person (first_name, last_name, age) values ('Jan', 'Kowalski', 15)")
    }

    def "should find one person"() {
        given:
            jdbcTemplate.queryForList("select first_name, last_name, age from person where last_name = ?", ["Kowalski"]) >> [[first_name: "Jan", last_name: "Kowalski", age: 20]]
        expect:
            sut.findByLastName("Kowalski") == [new Person("Jan", "Kowalski", 20)]
    }

    def "should find one person with stub"() {
        given:
            jdbcTemplate = Stub(JdbcTemplate)
            sut = new PersonDao(jdbcTemplate)
            jdbcTemplate.queryForList("select first_name, last_name, age from person where last_name = ?", ["Kowalski"]) >> [[first_name: "Jan", last_name: "Kowalski", age: 20]]
        expect:
            sut.findByLastName("Kowalski") == [new Person("Jan", "Kowalski", 20)]
    }

    def "should find many times person"() {
        given:
            jdbcTemplate.queryForList(_, _) >> [[first_name: "Jan", last_name: "Kowalski", age: 20]]
        expect:
            sut.findByLastName("Kowalski") == [new Person("Jan", "Kowalski", 20)]
            sut.findByLastName("Kowalski") == [new Person("Jan", "Kowalski", 20)]
    }

    def "should find many times person 2"() {
        given:
            jdbcTemplate.queryForList(_, _) >> [[first_name: "Jan", last_name: "Kowalski", age: 20]] >> [[first_name: "Jan", last_name: "Kowalski", age: 25]]
        expect:
            sut.findByLastName("Kowalski") == [new Person("Jan", "Kowalski", 20)]
            sut.findByLastName("Kowalski") == [new Person("Jan", "Kowalski", 25)]
    }

    def "should find many times person 3"() {
        given:
            jdbcTemplate.queryForList(_, _) >>> [
                    [[first_name: "Jan", last_name: "Kowalski", age: 20]],
                    [[first_name: "Jan", last_name: "Kowalski", age: 15]]]
        expect:
            sut.findByLastName("Kowalski") == [new Person("Jan", "Kowalski", 20)]
            sut.findByLastName("Kowalski") == [new Person("Jan", "Kowalski", 15)]
    }

    def "should throw exception on second find"() {
        given:
            jdbcTemplate.queryForList(_, _) >>
                    [[first_name: "Jan", last_name: "Kowalski", age: 20]] >>
                    { throw new DataRetrievalFailureException("Cannot retrieve data") }
        expect:
            sut.findByLastName("Kowalski") == [new Person("Jan", "Kowalski", 20)]
        when:
            sut.findByLastName("Kowalski")
        then:
            thrown(DataAccessException)
    }

    def "should throw exception on second find 2"() {
        given:
            int counter = 0
            jdbcTemplate.queryForList(_, _) >> {
                if (counter == 0) {
                    ++counter
                    [[first_name: "Jan", last_name: "Kowalski", age: 20]]
                } else {
                    throw new DataRetrievalFailureException("Cannot retrieve data")
                }
            }
        expect:
            sut.findByLastName("Kowalski") == [new Person("Jan", "Kowalski", 20)]
        when:
            sut.findByLastName("Kowalski")
        then:
            thrown(DataAccessException)
    }

    def "should find one person and check invocation"() {
        when:
            List result = sut.findByLastName("Kowalski")
        then:
            result == [new Person("Jan", "Kowalski", 20)]
            1 * jdbcTemplate.queryForList(_, _) >> [[first_name: "Jan", last_name: "Kowalski", age: 20]]
    }

    def "should find one person and check invocation with any parameters"() {
        when:
            List result = sut.findByLastName("Kowalski")
        then:
            result == [new Person("Jan", "Kowalski", 20)]
            1 * jdbcTemplate.queryForList(*_) >> [[first_name: "Jan", last_name: "Kowalski", age: 20]]
    }

    def "should find one person and check invocation with second parameter not Smith"() {
        when:
            List result = sut.findByLastName("Kowalski")
        then:
            result == [new Person("Jan", "Kowalski", 20)]
            1 * jdbcTemplate.queryForList(_, !(["Smith"] as Object[])) >> [[first_name: "Jan", last_name: "Kowalski", age: 20]]
    }

    def "should find one person and check invocation with first parameter not null"() {
        when:
            List result = sut.findByLastName("Kowalski")
        then:
            result == [new Person("Jan", "Kowalski", 20)]
            1 * jdbcTemplate.queryForList(!null, _) >> [[first_name: "Jan", last_name: "Kowalski", age: 20]]
    }
}
