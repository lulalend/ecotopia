package ru.itmo.ecotopia.service

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.itmo.ecotopia.handlers.mappers.CartMapper
import ru.itmo.ecotopia.model.JPAentities.CartEntity
import ru.itmo.ecotopia.model.responses.CartResponse
import ru.itmo.ecotopia.repositories.CartRepository
import ru.itmo.ecotopia.repositories.ShopUserRepository
import java.util.*

@Service
class CartServiceImpl(
    private val cartMapper: CartMapper,
    private val cartRepository: CartRepository,
    private val shopUserRepository: ShopUserRepository
): CartService {
    override fun createCart(): CartResponse {
        val cartEntity = CartEntity()
        cartRepository.save(cartEntity)

        return cartMapper.fromEntity(cartEntity.toCart())
    }

    override fun getCarts(): List<CartResponse> {
        val cartIterable = cartRepository.findAll()
        val carts = mutableListOf<CartResponse>()
        cartIterable.forEach {
            carts.add(cartMapper.fromEntity(it.toCart()))
        }
        return carts
    }

    override fun deleteCart(id: UUID, auth: Authentication) {
        val optionalCart = cartRepository.findById(id)
        val cartEntity = optionalCart.orElseThrow {
            IllegalArgumentException("Cart with id $id is not present")
        }
        val username = auth.name
        val user = shopUserRepository.findByUsername(username)

        if (user?.id == cartEntity.toCart().user.id)
            cartRepository.deleteById(id)
    }
}