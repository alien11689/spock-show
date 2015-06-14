package com.blogspot.przybyszd.spock.old11

import spock.lang.Specification

class OldTest extends Specification {
    def "should add numbers"() {
        given:
            int sum = 6
            int a = 4
        when:
            sum += a
        then:
            sum == old(sum) + 4
    }
}