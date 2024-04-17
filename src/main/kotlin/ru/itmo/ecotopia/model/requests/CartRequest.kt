package ru.itmo.ecotopia.model.requests

import ru.itmo.ecotopia.model.entities.ShopUser

data class CartRequest (
    val user: ShopUser
)