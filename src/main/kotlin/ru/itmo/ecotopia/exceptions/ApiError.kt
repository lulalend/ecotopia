package ru.itmo.ecotopia.exceptions

import org.springframework.http.HttpStatus

data class ApiError (
    val message: String? =  "Something went wrong :(",
    val status: HttpStatus = HttpStatus.BAD_REQUEST,
    val code: Int = status.value()
)