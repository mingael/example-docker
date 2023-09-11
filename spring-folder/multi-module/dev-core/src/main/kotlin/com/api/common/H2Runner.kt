package com.api.common

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class H2Runner(
    private val dataSource: DataSource,
    private val jdbcTemplate: JdbcTemplate
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        try {
            val connection = dataSource.connection
            val statement = connection.createStatement()
            statement.executeUpdate("CREATE TABLE account(id integer primary key, name varchar(255)) ")
        } catch (_: Exception) { }

        jdbcTemplate.execute("INSERT INTO account VALUES (1, 'test')")
    }

}