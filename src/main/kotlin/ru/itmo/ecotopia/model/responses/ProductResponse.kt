package ru.itmo.ecotopia.model.responses

import jakarta.persistence.Column
import ru.itmo.ecotopia.model.enums.CategoryProduct
import java.util.*

data class ProductResponse (
    val id: UUID,

    var name: String,

    var description: String?,

    var price: Double,

    var categoryProduct: CategoryProduct,

    var rating: Double,

    var amount: Int
)