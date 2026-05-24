package com.flightping.backend.common.health;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HealthControllerTest {

	@Test
	void healthReturnsOk() {
		HealthController controller = new HealthController();

		assertThat(controller.health().status()).isEqualTo("ok");
	}
}
