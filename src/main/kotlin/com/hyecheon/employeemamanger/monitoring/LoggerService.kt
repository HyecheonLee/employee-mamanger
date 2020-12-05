package com.hyecheon.employeemamanger.monitoring

import org.springframework.boot.actuate.health.*
import org.springframework.stereotype.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@Component
class LoggerService : HealthIndicator {
	companion object {
		const val LOGGER_SERVICE = "Logger Service"
	}

	override fun health(): Health {
		if (isLoggerServiceGood()) {
			return Health.up().withDetail(LOGGER_SERVICE, "Service is running").build()
		}
		return Health.down().withDetail(LOGGER_SERVICE, "Service is not available").build();
	}

	private fun isLoggerServiceGood() = run {
		false
	}
}