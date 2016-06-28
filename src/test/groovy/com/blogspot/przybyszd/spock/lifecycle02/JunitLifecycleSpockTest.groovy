package com.blogspot.przybyszd.spock.lifecycle02

import com.blogspot.przybyszd.spock.dto.Person
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import spock.lang.Stepwise

@Stepwise
class JunitLifecycleSpockTest extends LifecycleSpockTest {

    @BeforeClass
    static def setupSpecFromJunit() {
        println 'In setup spec - junit'
    }

    @Before
    def setupFromJunit() {
        println 'In setup - junit'
        person = new Person(firstName: 'Tom', lastName: 'Smith', age: 21)
    }

    @After
    def cleanupFromJunit() {
        println 'In cleanup - junit'
        person = null
    }

    @AfterClass
    static def cleanupSpecFromJunit() {
        println 'In cleanup spec - junit'
    }

    def 'should check firstName 2'() {
        setup:
            println 'setup in test 2'
            println 'should check firstName 2'
        expect:
            person.firstName == 'Tom'
        cleanup:
            println 'Cleanup after test 2'
    }
}
