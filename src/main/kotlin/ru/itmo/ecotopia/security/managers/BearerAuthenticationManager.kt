package ru.itmo.ecotopia.security.managers

import org.springframework.security.core.Authentication
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Component
import ru.itmo.ecotopia.security.BearerTokenAuthentication
import ru.itmo.ecotopia.service.TokenService

class BearerAuthenticationManager (
    private val tokenService: TokenService
) : AuthenticationManager {
    override fun authenticate(auth: Authentication?)
    : Authentication {
        if (auth is BearerTokenAuthentication) {
            val authenticatedToken = tokenService.verifyTokenAndGetAuthentication(auth.token)
            return authenticatedToken
        } else {
            throw IllegalArgumentException("Unsupported authentication type: ${auth?.javaClass?.simpleName}")
        }
    }
}