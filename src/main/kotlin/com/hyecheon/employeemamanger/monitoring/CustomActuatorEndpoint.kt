package com.hyecheon.employeemamanger.monitoring

import org.springframework.boot.actuate.endpoint.annotation.*
import org.springframework.stereotype.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@Endpoint(id = "custom")
@Component
class CustomActuatorEndpoint {

	@ReadOperation
	fun customEndpoint(username: String) = run {
		mapOf("Key" to "Value", "Name" to username)
	}
}