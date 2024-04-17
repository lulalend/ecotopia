package ru.itmo.ecotopia.security

import org.springframework.security.authentication.AbstractAuthenticationToken

class BearerTokenAuthentication (
    val token: String
) : AbstractAuthenticationToken(emptyList()) {
    override fun getCredentials(): Any = token

    override fun getPrincipal(): Any = token
}