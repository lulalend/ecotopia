package ru.itmo.ecotopia.model.entities

import java.util.*

data class ProductInOrder(
    val orderId: UUID,
    val productId: UUID,
    val amount: Int
)
