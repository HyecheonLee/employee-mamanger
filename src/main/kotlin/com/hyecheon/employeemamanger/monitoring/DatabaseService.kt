package com.hyecheon.employeemamanger.monitoring

import org.springframework.boot.actuate.health.*
import org.springframework.stereotype.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */

@Component
class DatabaseService : HealthIndicator {
	companion object {
		const val DATABASE_SERVICE = "DatabaseService"
	}

	override fun health(): Health {
		if (isDatabaseHealthGood()) {
			return Health.up().withDetail(DATABASE_SERVICE, "Service is running").build()
		}
		return Health.down().withDetail(DATABASE_SERVICE, "Service is not available").build();
	}

	private fun isDatabaseHealthGood() = run {
		true
	}
}