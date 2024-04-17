package ru.itmo.ecotopia.handlers.mappers

import org.springframework.stereotype.Service
import ru.itmo.ecotopia.model.JPAentities.CartEntity
import ru.itmo.ecotopia.model.entities.Cart
import ru.itmo.ecotopia.model.requests.CartRequest
import ru.itmo.ecotopia.model.responses.CartResponse
import java.util.*

@Service
class CartMapper: Mapper<CartEntity, Cart, CartRequest, CartResponse> {
    override fun toEntity(domain: CartRequest): CartEntity = CartEntity (
        id = UUID.randomUUID()
    )

    override fun fromEntity(entity: Cart): CartResponse = CartResponse (
        id = entity.id
    )

}