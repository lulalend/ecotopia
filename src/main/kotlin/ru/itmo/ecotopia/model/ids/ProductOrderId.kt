package ru.itmo.ecotopia.model.ids

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*

@Embeddable
data class ProductOrderId (
    @Column(name = "product_id")
    var productId: UUID? = null,
    @Column(name = "order_id")
    var orderId: UUID? = null
) : Serializable
