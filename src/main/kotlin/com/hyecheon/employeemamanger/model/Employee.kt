package com.hyecheon.employeemamanger.model

import java.util.*
import javax.persistence.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */

@Entity
data class Employee(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	val id: Long? = null,
	val name: String? = null,
	val email: String? = null,
	val jobTitle: String? = null,
	val phone: String? = null,
	val imageUrl: String? = null,
	@Column(nullable = false, updatable = false)
	val employeeCode: String = UUID.randomUUID().toString()
) : BaseEntity()