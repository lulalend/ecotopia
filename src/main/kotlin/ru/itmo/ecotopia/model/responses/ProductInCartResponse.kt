package ru.itmo.ecotopia.model.responses

import java.util.*

data class ProductInCartResponse(
    val cartId: UUID,
    val productId: UUID,
    val amount: Int?
)
