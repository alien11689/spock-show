package com.blogspot.przybyszd.spock.annotations08

import spock.lang.Ignore
import spock.lang.Specification

class IgnoreTest extends Specification {

    def "test 1"() {
        expect:
            1 == 1
    }

    @Ignore
    def "test 2"() {
        expect:
            1 == 1
    }

    def "test 3"() {
        expect:
            1 == 1
    }
}
