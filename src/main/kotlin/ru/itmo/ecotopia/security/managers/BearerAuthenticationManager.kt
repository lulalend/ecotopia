package ru.itmo.ecotopia.security.managers

import org.springframework.security.core.Authentication
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken
import ru.itmo.ecotopia.service.TokenService

class BearerAuthenticationManager (
    private val tokenService: TokenService
) : AuthenticationManager {
    override fun authenticate(auth: Authentication?)
    : Authentication {
        if (auth is BearerTokenAuthenticationToken) {
            return tokenService.verifyTokenAndGetAuthentication(auth.token)
        } else {
            throw IllegalArgumentException("Unsupported authentication type: ${auth?.javaClass?.simpleName}")
        }
    }
}