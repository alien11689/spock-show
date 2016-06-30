package com.blogspot.przybyszd.spock.spring06

import com.blogspot.przybyszd.spock.bean.PersonDao
import groovy.transform.CompileStatic
import org.spockframework.spring.xml.SpockMockFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@CompileStatic
class TestPersonContextConfiguration {
    @Bean
    SpockMockFactoryBean<PersonDao> getSpockMockFactoryBeanForPersonDao() {
        return new SpockMockFactoryBean<PersonDao>(PersonDao)
    }
}
