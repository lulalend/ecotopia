package ru.itmo.ecotopia.handlers.mappers

import org.springframework.stereotype.Service
import ru.itmo.ecotopia.model.dbEntities.ProductInCartEntity
import ru.itmo.ecotopia.model.entities.ProductInCart
import ru.itmo.ecotopia.model.ids.ProductCartId
import ru.itmo.ecotopia.model.requests.ProductInCartRequest
import ru.itmo.ecotopia.model.responses.ProductInCartResponse
import ru.itmo.ecotopia.repositories.CartRepository
import ru.itmo.ecotopia.repositories.ProductRepository

@Service
class ProductInCartMapper: Mapper<ProductInCartEntity, ProductInCart, ProductInCartRequest, ProductInCartResponse> {
    override fun toEntity(domain: ProductInCartRequest): ProductInCartEntity {
        val productCartId = ProductCartId (
            productId = domain.productId,
            cartId = domain.cartId
        )

        return ProductInCartEntity(
            productCartId = productCartId,
            amount = null
        )
    }

    override fun fromEntity(entity: ProductInCart): ProductInCartResponse = ProductInCartResponse (
        cartId = entity.cartId,
        productId = entity.productId,
        amount = entity.amount
    )
}