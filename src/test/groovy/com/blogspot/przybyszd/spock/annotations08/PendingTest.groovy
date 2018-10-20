package com.blogspot.przybyszd.spock.annotations08

import spock.lang.PendingFeature
import spock.lang.Specification

class PendingTest extends Specification {
    @PendingFeature(reason = 'waiting for JIRA-1234')
    def 'test 2'() {
        expect:
            throw new RuntimeException()
    }
}
