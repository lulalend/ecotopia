package ru.itmo.ecotopia.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class UserAuthentication(
    private val username: String,
    private val authorities: Collection<GrantedAuthority>

) : Authentication {
    private var authenticated = true
    override fun getName(): String = username

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun getCredentials(): Any = ""

    override fun getDetails(): Any = ""

    override fun getPrincipal(): Any = username

    override fun isAuthenticated(): Boolean = authenticated

    override fun setAuthenticated(authenticated: Boolean) {
        this.authenticated = authenticated
    }
}