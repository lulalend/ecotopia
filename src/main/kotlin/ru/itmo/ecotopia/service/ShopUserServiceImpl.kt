package ru.itmo.ecotopia.service

import org.springframework.stereotype.Service
import ru.itmo.ecotopia.model.dbEntities.ShopUserEntity
import ru.itmo.ecotopia.model.entities.ShopUser
import ru.itmo.ecotopia.repositories.ShopUserRepository

@Service
class ShopUserServiceImpl (
    private val shopUserRepository: ShopUserRepository
): ShopUserService {

    override fun findUserByUsername(username: String): ShopUser? {
        val userEntity = shopUserRepository.findByUsername(username)
        return userEntity?.toShopUser()
    }

    override fun createUser(user: ShopUserEntity): ShopUser {
        return shopUserRepository.save(user).toShopUser()
    }

}