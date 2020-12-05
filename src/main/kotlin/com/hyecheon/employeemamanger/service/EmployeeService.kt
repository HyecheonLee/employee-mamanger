package com.hyecheon.employeemamanger.service

import com.hyecheon.employeemamanger.exception.*
import com.hyecheon.employeemamanger.model.*
import com.hyecheon.employeemamanger.repository.*
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@Service
@Transactional
class EmployeeService(
	private val employeeRepository: EmployeeRepository
) {
	fun addEmployee(employee: Employee) = run {
		employeeRepository.save(employee)
	}

	fun findAllEmployees() = run {
		employeeRepository.findAll()
	}

	fun findEmployeeById(id: Long) = run {
		employeeRepository.findById(id).orElseThrow { UserNotFoundException("User by id $id was not found") }!!
	}

	fun updateEmployee(employee: Employee) = run {
		employeeRepository.save(employee)
	}

	fun deleteEmployee(id: Long) = run {
		employeeRepository.deleteById(id)
	}
}