package ru.itmo.ecotopia.service

import ru.itmo.ecotopia.model.dbEntities.ShopUserEntity
import ru.itmo.ecotopia.model.entities.ShopUser

interface ShopUserService {
    fun findUserByUsername(username: String): ShopUser?
    fun createUser(user: ShopUserEntity): ShopUser
//    fun getUserPassword(): String
//    fun getUsers(): List<CartResponse>
}