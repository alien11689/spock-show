package com.blogspot.przybyszd.spock.annotations08

import spock.lang.FailsWith
import spock.lang.IgnoreIf
import spock.lang.Specification

class IgnoreIfTest extends Specification {

//    @IgnoreIf({os.windows})
//    @IgnoreIf({os.linux})
    @IgnoreIf({System.getProperty("os.name").contains("Linux")})
    def "test 1"() {
        expect:
            1 == 1
    }
}
