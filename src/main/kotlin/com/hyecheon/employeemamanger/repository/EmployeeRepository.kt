package com.hyecheon.employeemamanger.repository

import com.hyecheon.employeemamanger.model.*
import org.springframework.data.jpa.repository.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
interface EmployeeRepository : JpaRepository<Employee, Long> {
}