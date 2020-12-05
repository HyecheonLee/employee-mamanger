package com.hyecheon.employeemamanger.web

import com.fasterxml.jackson.databind.*
import com.hyecheon.employeemamanger.model.*
import com.hyecheon.employeemamanger.service.*
import org.hamcrest.core.Is.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.*
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.test.autoconfigure.web.servlet.*
import org.springframework.boot.test.mock.mockito.*
import org.springframework.http.*
import org.springframework.test.context.*
import org.springframework.test.context.junit.jupiter.*
import org.springframework.test.web.servlet.*
import org.springframework.test.web.servlet.request.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.web.util.*
import java.lang.RuntimeException


/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [EmployeeResource::class])
internal class EmployeeResourceTest {
	@Autowired
	private lateinit var mockMvc: MockMvc

	@MockBean
	private lateinit var employeeService: EmployeeService

	@Autowired
	lateinit var objectMapper: ObjectMapper

	@DisplayName("모든 고객 정보 가져오기")
	@Test
	internal fun test01() {
		//given
		val testEmployees = getTestEmployees()
		given(employeeService.findAllEmployees()).willReturn(testEmployees)

		mockMvc
			.perform(MockMvcRequestBuilders.get("/employee"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$[0].id", `is`(testEmployees[0].id!!.toInt())))
	}

	@DisplayName("id로 고객 한명 정보 가져오기")
	@Test
	internal fun test02() {
		val employees = getTestEmployees()[0]
		given(employeeService.findEmployeeById(anyLong())).willReturn(employees)

		mockMvc
			.perform(get("/employee/${employees.id}"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.id", `is`(employees.id!!.toInt())))
			.andExpect(jsonPath("$.name", `is`(employees.name)))
			.andExpect(jsonPath("$.email", `is`(employees.email)))
			.andExpect(jsonPath("$.jobTitle", `is`(employees.jobTitle)))
			.andExpect(jsonPath("$.phone", `is`(employees.phone)))
			.andExpect(jsonPath("$.imageUrl", `is`(employees.imageUrl)))
	}


	@DisplayName("생성 하기")
	@Test
	internal fun test03() {
		val employee = getTestEmployee()
		val givenEmployee = employee.copy(id = null)
		given(employeeService.addEmployee(givenEmployee)).willReturn(employee)

		mockMvc
			.perform(
				post("/employee")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(givenEmployee))
			)
			.andExpect(status().isCreated)
			.andExpect(jsonPath("$.id", `is`(employee.id!!.toInt())))
			.andExpect(jsonPath("$.name", `is`(employee.name)))
			.andExpect(jsonPath("$.email", `is`(employee.email)))
			.andExpect(jsonPath("$.jobTitle", `is`(employee.jobTitle)))
			.andExpect(jsonPath("$.phone", `is`(employee.phone)))
			.andExpect(jsonPath("$.imageUrl", `is`(employee.imageUrl)))
	}

	@DisplayName("Id가 있으면 수정")
	@Test
	internal fun test04() {
		val employee = getTestEmployee()
		mockMvc
			.perform(
				put("/employee")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(employee))
			)
			.andExpect(status().isOk)

	}

	@DisplayName("Id가 없으면 오류")
	@Test
	internal fun test05() {
		Assertions.assertThrows(NestedServletException::class.java) {
			val employee = getTestEmployee()
			mockMvc
				.perform(
					put("/employee")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employee.copy(null)))
				)
				.andExpect(status().is5xxServerError)
		}
	}

	@DisplayName("삭제하면 컨텐츠가 없다.")
	@Test
	internal fun test06() {
		val employee = getTestEmployee()
		mockMvc
			.perform(
				delete("/employee/${employee.id}")
			)
			.andExpect(status().isOk)
			.andExpect(content().string(""))
	}

	private fun getTestEmployee() = run {
		Employee(1, "name - 1", "email - 1", "job title - 1", "010-1111-1111", "image-url - 1");
	}

	private fun getTestEmployees() = run {
		arrayListOf(
			Employee(1, "name - 1", "email - 1", "job title - 1", "010-1111-1111", "image-url - 1"),
			Employee(2, "name - 2", "email - 2", "job title - 2", "010-2222-2222", "image-url - 2"),
			Employee(3, "name - 3", "email - 3", "job title - 3", "010-3333-3333", "image-url - 3")
		)
	}
}