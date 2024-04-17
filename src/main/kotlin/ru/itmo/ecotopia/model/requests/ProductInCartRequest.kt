package ru.itmo.ecotopia.model.requests

import java.util.UUID

data class ProductInCartRequest (
    val cartId: UUID,
    val productId: UUID,
)