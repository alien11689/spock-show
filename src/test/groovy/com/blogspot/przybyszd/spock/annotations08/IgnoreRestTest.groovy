package com.blogspot.przybyszd.spock.annotations08

import spock.lang.IgnoreRest
import spock.lang.Specification

class IgnoreRestTest extends Specification {

    def 'test 1'() {
        expect:
            1 == 1
    }

    @IgnoreRest
    def 'test 2'() {
        expect:
            1 == 1
    }

    def 'test 3'() {
        expect:
            1 == 1
    }
}
