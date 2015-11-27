package com.blogspot.przybyszd.spock.annotations08

import spock.lang.Specification
import spock.lang.Timeout

import java.util.concurrent.TimeUnit

class TimeoutTest extends Specification {

    @Timeout(value = 750, unit = TimeUnit.MILLISECONDS)
    def 'test 1'() {
        expect:
            1 == 1
    }
}
