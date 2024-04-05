package ru.itmo.ecotopia.entities

data class Product (
    val id: Int,
    var name: String,
    var description: String,
    var price: Double,
    val category: Category,

)