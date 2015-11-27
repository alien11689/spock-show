package com.blogspot.przybyszd.spock.annotations08

import spock.lang.Specification
import spock.lang.Title

@Title('Title annotation is tested in this specification')
class TitleTest extends Specification {

    def 'test 1'() {
        expect:
            1 == 1
    }
}
