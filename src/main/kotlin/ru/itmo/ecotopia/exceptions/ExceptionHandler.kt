package ru.itmo.ecotopia.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception
import java.lang.IllegalArgumentException

@RestControllerAdvice
class ExceptionController {
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleException(exception: Exception): ResponseEntity<String> {
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}