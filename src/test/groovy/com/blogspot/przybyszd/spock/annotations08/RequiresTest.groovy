package com.blogspot.przybyszd.spock.annotations08

import spock.lang.Requires
import spock.lang.Specification

class RequiresTest extends Specification {

//    @Requires({os.windows})
//    @Requires({os.linux})
    @Requires({ System.getProperty('os.name').contains('windows') })
    def 'test 1'() {
        expect:
            1 == 1
    }
}
