package com.blogspot.przybyszd.spock.configuration

import groovy.transform.CompileStatic
import org.apache.commons.dbcp.BasicDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

import javax.sql.DataSource

@Configuration
@ComponentScan('com.blogspot.przybyszd.spock')
@CompileStatic
class PersonContextConfiguration {
    @Bean
    JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource)
    }

    @Bean(destroyMethod = 'close')
    DataSource getDataSource() {
        return new BasicDataSource(
                driverClassName: 'org.h2.Driver',
                url: '''jdbc:h2:mem:personDB;DB_CLOSE_DELAY=1000;INIT=runscript from 'classpath:db/person.sql';''',
                username: 'sa',
                password: ''
        )
    }
}
