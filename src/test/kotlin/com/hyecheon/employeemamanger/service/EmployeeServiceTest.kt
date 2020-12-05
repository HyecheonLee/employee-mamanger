package com.hyecheon.employeemamanger.service

import com.hyecheon.employeemamanger.exception.*
import com.hyecheon.employeemamanger.model.*
import com.hyecheon.employeemamanger.repository.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.test.context.*
import org.springframework.test.context.*
import org.springframework.transaction.annotation.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */

@ActiveProfiles("test")
@SpringBootTest
@Transactional
internal class EmployeeServiceTest {

	@Autowired
	private lateinit var employeeService: EmployeeService

	@Autowired
	private lateinit var employeeRepository: EmployeeRepository

	@BeforeEach
	internal fun setUp() {
		employeeRepository.save(Employee(1, "name - 1", "email - 1", "job title - 1", "010-1111-1111", "image-url - 1"))
		employeeRepository.save(Employee(2, "name - 2", "email - 2", "job title - 2", "010-2222-2222", "image-url - 2"))
		employeeRepository.save(Employee(3, "name - 3", "email - 3", "job title - 3", "010-3333-3333", "image-url - 3"))
	}

/*	@AfterEach
	internal fun tearDown() {
		employeeRepository.deleteAll()
	}*/

	@DisplayName("employee 추가시 id가 null 이 아니다.")
	@Test
	internal fun test01() {
		val employee = Employee(null, "name", "email", "job title", "010-0000-0000", "image-url")

		val savedEmployee = employeeService.addEmployee(employee)

		Assertions.assertThat(savedEmployee.id).isNotNull
	}

	@DisplayName("employee 모든 사용자 가져오기")
	@Test
	internal fun test02() {
		val employees = employeeService.findAllEmployees()
		Assertions.assertThat(employees.size).isEqualTo(3)
	}

	@DisplayName("employee id로 검색")
	@Test
	internal fun test03() {
		val employees = employeeService.findAllEmployees()
		val findEmployee = employeeService.findEmployeeById(employees[0].id!!)
		Assertions.assertThat(findEmployee.id).isEqualTo(employees[0].id!!)
	}

	@DisplayName("employee id가 없을때 오류 발생")
	@Test
	internal fun test04() {
		org.junit.jupiter.api.Assertions.assertThrows(UserNotFoundException::class.java) {
			employeeService.findEmployeeById(0)
		}
	}

	@DisplayName("employ update 테스트")
	@Test
	internal fun test05() {
		val employees = employeeService.findAllEmployees()
		val employee = employees[0]!!

		employeeService.updateEmployee(employee.copy(name = "updateName"))
		val findEmployee = employeeService.findEmployeeById(employee.id!!)
		Assertions.assertThat(findEmployee.name).isEqualTo("updateName")
	}

	@DisplayName("employ delete 테스트")
	@Test
	internal fun tes06() {
		val employees = employeeService.findAllEmployees()
		val totalSize = employees.size
		val employee = employees[0]!!
		employeeService.deleteEmployee(employee.id!!)
		Assertions.assertThat(employeeService.findAllEmployees().size).isEqualTo(totalSize - 1)
	}
}