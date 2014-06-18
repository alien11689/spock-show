package com.blogspot.przybyszd.spock.configuration

import org.apache.commons.dbcp.BasicDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

import javax.sql.DataSource

@Configuration
@ComponentScan("com.blogspot.przybyszd.spock")
class PersonContextConfiguration {
    @Bean
    JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource)
    }

    @Bean
    DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource()
        basicDataSource.setDriverClassName("org.h2.Driver")
        basicDataSource.setUrl("jdbc:h2:mem:personDB;DB_CLOSE_DELAY=1000;INIT=runscript from 'classpath:db/person.sql';")
        basicDataSource.setUsername("sa")
        basicDataSource.setPassword("")
        return basicDataSource
    }
}
