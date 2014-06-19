package com.blogspot.przybyszd.spock.annotations08

import spock.lang.Narrative
import spock.lang.Specification

@Narrative("""Multiline narrative annotation
is tested in this specification""")
class NarrativeTest extends Specification {

    def "test 1"() {
        expect:
            1 == 1
    }
}
