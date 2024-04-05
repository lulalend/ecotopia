package ru.itmo.ecotopia.model.jPAEntities

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.beans.FatalBeanException
import ru.itmo.ecotopia.model.entities.ProductInCart
import ru.itmo.ecotopia.model.ids.ProductCartId

@Entity
@Table(name = "products_carts")
data class ProductInCartEntity (
    @EmbeddedId
    var productCartId: ProductCartId? = null,

    var amount: Int? = null
) {
    fun toProductInCart() : ProductInCart = ProductInCart (
        cartId = productCartId?.cartId ?: throw FatalBeanException("cart's id in products_carts is null"),
        productId = productCartId?.productId ?: throw FatalBeanException("product's id in products_carts is null"),
        amount = amount ?: throw FatalBeanException("amount of products in products_carts is null")
    )
}