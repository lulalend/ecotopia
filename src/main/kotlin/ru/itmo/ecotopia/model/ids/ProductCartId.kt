package ru.itmo.ecotopia.model.ids

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Id
import java.io.Serializable
import java.util.*

@Embeddable
data class ProductCartId(
    @Column(name = "product_id")
    var productId: UUID? = null,
    @Column(name = "cart_id")
    var cartId: UUID? = null
) : Serializable
