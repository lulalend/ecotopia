package ru.itmo.ecotopia.model.requests

import jakarta.validation.constraints.Email

data class SignUpRequest (
    val username: String,
    @Email(message = "Email must be in the a format user@example.com")
    val email: String,
    val password: String,
)