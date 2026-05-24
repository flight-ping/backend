package com.flightping.backend.common.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class DatabaseConnectionTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void databaseConnectionIsAvailable() {
		Integer result = jdbcTemplate.queryForObject("select 1", Integer.class);

		assertThat(result).isEqualTo(1);
	}
}
