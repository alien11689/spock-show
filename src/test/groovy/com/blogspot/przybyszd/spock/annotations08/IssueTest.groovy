package com.blogspot.przybyszd.spock.annotations08

import spock.lang.Issue
import spock.lang.Specification

class IssueTest extends Specification {

    @Issue(['http://example.org/mantis/view.php?id=12345', 'http://example.org/mantis/view.php?id=23'])
    def 'test 1'() {
        expect:
            1 == 1
    }
}
