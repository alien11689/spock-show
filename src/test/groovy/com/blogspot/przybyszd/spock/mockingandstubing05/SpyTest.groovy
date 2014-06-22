package com.blogspot.przybyszd.spock.mockingandstubing05

import spock.lang.Specification

class SpyTest extends Specification {

    List sut = Spy(ArrayList, constructorArgs: [10])

    def "should use spy on list"() {
        given:
            sut.add(1) >> {
                callRealMethod()
            }
            sut.size() >> 10
        when:
            sut.add(1)
        then:
            sut.size() == 10
            sut.get(0) == 1
    }
}
