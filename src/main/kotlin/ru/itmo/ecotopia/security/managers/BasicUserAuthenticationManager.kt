package ru.itmo.ecotopia.security.managers

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import ru.itmo.ecotopia.security.UserAuthentication
import ru.itmo.ecotopia.security.configuration.SecurityUserProperties
import ru.itmo.ecotopia.service.ShopUserService

@Component
class BasicUserAuthenticationManager(
    private val shopUserService: ShopUserService,
    private val passwordEncoder: PasswordEncoder,
    private val securityUserProperties: SecurityUserProperties
) : AuthenticationManager {

    override fun authenticate(authentication: Authentication): Authentication {
        println(authentication.name)
        val username = authentication.name
        val userFromDb = shopUserService.findUserByUsername(username) ?: throw BadCredentialsException("Login is incorrect")
        val passwordFromDb = userFromDb.password

        if (!passwordEncoder.matches(authentication.credentials as String, passwordFromDb)) {
            throw BadCredentialsException("Password is incorrect")
        }

        val role = userFromDb.role

        val permissions = mutableListOf<GrantedAuthority>()

        val permissionsForRole = securityUserProperties.permissions.get(role)
                ?: throw Exception("An unexpected role retrieved from database")
        permissions.addAll(permissionsForRole.map { SimpleGrantedAuthority(it.name) })

        println(permissions)
        if (securityUserProperties.adminUsers.contains(username)) {
            val adminPermissions = securityUserProperties.permissions[securityUserProperties.adminPermissionName]
                ?: throw Exception("Admin permissions not found in config")
            permissions.addAll(adminPermissions.map { SimpleGrantedAuthority(it.name) })
        }

        return UserAuthentication(username, permissions)
    }
}