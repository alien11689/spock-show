package com.blogspot.przybyszd.spock.annotations08

import com.blogspot.przybyszd.spock.bean.PersonDao
import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.AutoCleanup
import spock.lang.Specification

class AutoCleanupTest extends Specification {

    JdbcTemplate jdbcTemplate = Mock(JdbcTemplate)

    @AutoCleanup(value = 'close', quiet = true)
    PersonDao sut = new PersonDao(jdbcTemplate)

    def 'test 1'() {
        expect:
            sut != null
    }
}
