package ru.itmo.ecotopia.controllers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import ru.itmo.ecotopia.model.entities.ShopUser
import ru.itmo.ecotopia.model.requests.SignUpRequest
import ru.itmo.ecotopia.model.responses.CartResponse
import ru.itmo.ecotopia.service.RegisterService
import ru.itmo.ecotopia.service.TokenService

@RestController
@RequestMapping("/sign_up")
class SignUpController(
    private val registerService: RegisterService
) {
    @PostMapping
    fun createToken(
        @RequestBody signUpRequest: SignUpRequest
    ) : ResponseEntity<ShopUser> =
        ResponseEntity<ShopUser>(registerService.registerNewUser(signUpRequest), HttpStatus.CREATED)

}