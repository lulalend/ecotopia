package ru.itmo.ecotopia.service

import org.springframework.security.core.Authentication
import ru.itmo.ecotopia.model.responses.CartResponse
import java.util.*

interface CartService {
    fun createCart(): CartResponse
    fun getCarts(): List<CartResponse>
    fun deleteCart(id: UUID, auth: Authentication)
}