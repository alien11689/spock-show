package com.blogspot.przybyszd.spock.pbt12

import groovy.transform.Immutable
import spock.genesis.Gen
import spock.genesis.transform.Iterations
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.annotation.RetentionPolicy
import java.util.concurrent.atomic.AtomicInteger
import java.util.regex.Pattern

@Unroll
class PropertyBaseTest extends Specification {

    def 'should double reverse string equals provided #input'() {
        expect:
            input.reverse().reverse() == input
        where:
            input << Gen.string.take(10)
    }

    def 'should limit size  #input'() {
        expect:
            input.size() in (5..20)
        where:
            input << Gen.string(5, 20).take(10)
    }

    @Shared
    AtomicInteger atomicInteger = new AtomicInteger(0)

    @Iterations(100)
    def 'should limit iterations #input'() {
        expect:
            atomicInteger.incrementAndGet() <= 100
        where:
            input << Gen.string
    }

    @Iterations(10)
    def 'should generate regex #input'() {
        expect:
            input ==~ /id\d{1,10}/
        where:
            input << Gen.string(Pattern.compile(/id\d{1,10}/))
    }

    @Iterations(10)
    def 'should combine generators #input1 #input2'() {
        expect:
            (input1 + input2) < 101
        where:
            input1 << Gen.integer(0, 100)
            input2 << Gen.double.filter { it > 0 && it < 1 }
    }

    @Iterations(10)
    def 'should select from list #input'() {
        expect:
            input in (RetentionPolicy.values())
        where:
            input << Gen.any(RetentionPolicy.values())
    }

    @Iterations(10)
    def 'should generate list #input'() {
        expect:
            input instanceof List
        where:
            input << Gen.list(Gen.integer)
    }

    @Iterations(10)
    def 'should generate map #input'() {
        expect:
            input instanceof Map
        where:
            input << Gen.map(Gen.string(5), Gen.integer)
    }


    @Iterations(10)
    def 'should generate object using map constructor #input'() {
        expect:
            input.equals(input)
        where:
            input << Gen.type(I,
                    a: Gen.integer(1, 10),
                    b: Gen.string(3, 5)
            )
    }

    @Iterations(10)
    def 'should generate object using typed constructor #input'() {
        expect:
            input.equals(input)
        where:
            input << Gen.type(I,
                    Gen.integer(1, 10),
                    Gen.string(3, 5)
            ).filter { it.a + it.b.size() > 10 }
    }

    @Immutable
    static class I {
        Integer a
        String b
    }

    def 'should generate finite iterations #input'() {
        expect:
            input in (1..10) || input == 100 || input == 200
        where:
            input << Gen.these((1..10)).then([100, 200])
    }

    @Iterations(5)
    def 'should generate with seed #input'() {
        expect:
            input in [
                    'A' as char,
                    'O' as char,
                    'K' as char,
                    'H' as char,
                    'D' as char,
            ]
        where:
            input << Gen.character.filter { it in 'A'..'Z' }.seed(1000L)
    }
}