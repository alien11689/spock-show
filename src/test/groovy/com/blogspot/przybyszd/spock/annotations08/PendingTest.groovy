package com.blogspot.przybyszd.spock.annotations08

import spock.lang.PendingFeature
import spock.lang.Specification

class PendingTest extends Specification {

    def 'test 1'() {
        expect:
            1 == 1
    }

    @PendingFeature
    def 'test 2'() {
        expect:
            throw new RuntimeException()
    }

    def 'test 3'() {
        expect:
            1 == 1
    }
}
