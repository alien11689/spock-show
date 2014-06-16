package com.blogspot.przybyszd.spock.dto

import groovy.transform.Canonical

@Canonical
class Person {
    String firstName
    String lastName
    Integer age

    boolean isAsult() {
        age >= 18
    }
}
