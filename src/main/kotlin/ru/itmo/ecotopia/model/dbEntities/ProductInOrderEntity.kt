package ru.itmo.ecotopia.model.dbEntities

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.beans.FatalBeanException
import ru.itmo.ecotopia.model.entities.ProductInCart
import ru.itmo.ecotopia.model.entities.ProductInOrder
import ru.itmo.ecotopia.model.ids.ProductOrderId

@Entity
@Table(name = "products_orders")
data class ProductInOrderEntity (
    @EmbeddedId
    var productOrderId: ProductOrderId? = null,

    var amount: Int? = null
) {
    fun toProductInOrder() : ProductInOrder = ProductInOrder (
        orderId = this.productOrderId?.orderId ?: throw FatalBeanException("order's id in products_orders is null"),
        productId = this.productOrderId?.productId ?: throw FatalBeanException("product's id in products_orders is null"),
        amount = this.amount ?: throw FatalBeanException("amount of products in products_orders is null")
    )
}