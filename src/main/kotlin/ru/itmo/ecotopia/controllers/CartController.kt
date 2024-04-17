package ru.itmo.ecotopia.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import ru.itmo.ecotopia.model.responses.CartResponse
import ru.itmo.ecotopia.service.CartService
import java.util.*

@RestController
@RequestMapping("/api/carts")
class CartController (
    private val cartService: CartService
) {
    @PostMapping
    fun createCart(): ResponseEntity<CartResponse> =
        ResponseEntity<CartResponse>(cartService.createCart(), HttpStatus.CREATED)

    @GetMapping
    fun getCarts(): ResponseEntity<List<CartResponse>> =
        ResponseEntity.ok(cartService.getCarts())

    @DeleteMapping("/{id}")
    fun deleteCart(@PathVariable id: UUID, auth: Authentication): ResponseEntity<Unit> =
        ResponseEntity(cartService.deleteCart(id, auth), HttpStatus.NO_CONTENT)
}