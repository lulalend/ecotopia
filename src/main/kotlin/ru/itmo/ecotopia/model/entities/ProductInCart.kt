package ru.itmo.ecotopia.model.entities

import java.util.*

data class ProductInCart (
    val cartId: UUID,
    val productId: UUID,
    val amount: Int?
)