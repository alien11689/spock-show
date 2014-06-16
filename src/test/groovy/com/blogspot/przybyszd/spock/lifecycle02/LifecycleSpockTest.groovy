package com.blogspot.przybyszd.spock.lifecycle02

import com.blogspot.przybyszd.spock.dto.Person
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class LifecycleSpockTest extends Specification {

    @Shared
    StringWriter writer

    Person person

    def setupSpec() {
        println "In setup spec"
        writer = new StringWriter()
    }

    def setup() {
        println "In setup"
        person = new Person(firstName: "Tom", lastName: "Smith", age: 21)
    }

    def cleanup() {
        println "In cleanup"
        person = null
    }

    def cleanupSpec() {
        println "In cleanup spec"
        writer.close()
    }

    def "should check firstName"() {
        println "should check firstName"
        expect:
            person.firstName == "Tom"
    }

    def "should check lastName"() {
        println "should check lastName"
        expect:
            person.lastName == "Smith"
    }

    def "should check age"() {
        println "should check age"
        expect:
            person.age == 21
    }
}
