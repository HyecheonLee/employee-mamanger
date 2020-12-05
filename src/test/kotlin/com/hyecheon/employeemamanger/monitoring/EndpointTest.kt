package com.hyecheon.employeemamanger.monitoring

import com.fasterxml.jackson.core.type.*
import com.fasterxml.jackson.databind.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.test.context.*
import org.springframework.boot.test.web.client.*
import org.springframework.test.context.junit.jupiter.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class EndpointTest {
	@Autowired
	lateinit var testRestTemplate: TestRestTemplate

	@Autowired
	lateinit var objectMapper: ObjectMapper

	@DisplayName("/actuator/health databaseService Endpoint가 존재 한다.")
	@Test
	internal fun dataServiceEndPointTest() {
		val result = testRestTemplate.getForEntity<String>("/actuator/health")
		val body = result.body!!
		Assertions.assertThat(body).contains("DatabaseService")
		Assertions.assertThat(body).contains("Service is running")
	}

	@DisplayName("/actuator/health loggerServiceEndPoint가 존재 한다.")
	@Test
	internal fun loggerServiceEndPointTest() {
		val result = testRestTemplate.getForEntity<String>("/actuator/health")
		val body = result.body!!
		Assertions.assertThat(body).contains("DatabaseService")
		Assertions.assertThat(body).contains("Service is not available")
	}

	@DisplayName("/actuator/custom CustomActuatorEndpoint가 존재 한다.")
	@Test
	internal fun customActuatorEndpointTest() {
		val expectedName = "TestUsername"
		val result = testRestTemplate.getForEntity<String>("/actuator/custom?username=${expectedName}")
		val body = result.body!!
		val responseBody = objectMapper.readValue(body, object : TypeReference<Map<String, String>>() {})
		mapOf("Key" to "Value", "Name" to expectedName)
		Assertions.assertThat(responseBody).isEqualTo(mapOf("Key" to "Value", "Name" to expectedName))
	}
}