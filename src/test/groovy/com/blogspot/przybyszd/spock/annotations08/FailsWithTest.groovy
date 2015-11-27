package com.blogspot.przybyszd.spock.annotations08

import spock.lang.FailsWith
import spock.lang.Specification

class FailsWithTest extends Specification {

    @FailsWith(RuntimeException)
    def 'test 1'() {
        expect:
            throw new RuntimeException()
    }
}
