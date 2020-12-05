package com.hyecheon.employeemamanger

import com.hyecheon.employeemamanger.model.*
import com.hyecheon.employeemamanger.service.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@RestController
@RequestMapping(value = ["/employee"])
class EmployeeResource(
	private val employeeService: EmployeeService
) {

	@GetMapping
	fun getAllEmployees() = run {
		ResponseEntity(employeeService.findAllEmployees(), HttpStatus.OK)
	}

	@GetMapping(value = ["/{id}"])
	fun getEmployee(@PathVariable id: Long) = run {
		ResponseEntity(employeeService.findEmployeeById(id), HttpStatus.OK)
	}

	@PostMapping
	fun addEmployee(@RequestBody employee: Employee) = run {
		val newEmployee = employeeService.addEmployee(employee)
		ResponseEntity(newEmployee, HttpStatus.CREATED)
	}

	@PutMapping(value = ["/{id}", ""])
	fun updateEmployee(@PathVariable(required = false) id: Long?, @RequestBody employee: Employee) = run {
		val updatableEmployee: Employee = if (id != null) {
			employee.copy(id = id)
		} else {
			employee
		}
		ResponseEntity(employeeService.updateEmployee(updatableEmployee), HttpStatus.OK)
	}

	@DeleteMapping("/{id}")
	fun deleteEmployee(@PathVariable id: Long) = run {
		employeeService.deleteEmployee(id)
		ResponseEntity<Any>(HttpStatus.OK)
	}
}