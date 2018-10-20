package com.blogspot.przybyszd.spock.annotations08

import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Specification

class RetryTest extends Specification {

    @Shared
    int counter = 0

    @Retry(count = 10)
    def 'test'() {
        given:
            println "Test ${++counter}"
        expect:
            new Random().nextBoolean()
    }
}
