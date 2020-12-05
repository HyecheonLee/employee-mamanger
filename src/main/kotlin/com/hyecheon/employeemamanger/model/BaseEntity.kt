package com.hyecheon.employeemamanger.model

import org.springframework.data.annotation.*
import org.springframework.data.jpa.repository.config.*
import java.io.*
import java.time.*
import javax.persistence.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@EnableJpaAuditing
@MappedSuperclass
abstract class BaseEntity : Serializable {
	@CreatedDate
	@Column(updatable = false)
	val createdDate: LocalDateTime = LocalDateTime.now()

	@LastModifiedDate
	@Column(updatable = true)
	val lastModifiedDate: LocalDateTime = LocalDateTime.now()
}