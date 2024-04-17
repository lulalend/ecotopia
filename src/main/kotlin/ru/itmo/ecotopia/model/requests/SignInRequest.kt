package ru.itmo.ecotopia.model.requests

import jakarta.validation.constraints.Email

data class SignInRequest (
    val username: String,
    val password: String,
)