package ru.itmo.ecotopia.controllers

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itmo.ecotopia.service.TokenService
import org.springframework.security.core.Authentication

@RestController
@RequestMapping("/api/sign_in")
class SignInController (
    private val tokenService: TokenService
) {
    @PostMapping
    fun login(authentication: Authentication) : String {
        println(authentication)
        val token = tokenService.generateToken(authentication)
        println(token)
        return token
    }

}