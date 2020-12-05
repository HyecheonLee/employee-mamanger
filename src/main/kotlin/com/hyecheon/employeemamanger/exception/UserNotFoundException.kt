package com.hyecheon.employeemamanger.exception

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
class UserNotFoundException : RuntimeException {
	constructor() : super()
	constructor(message: String) : super(message)
}