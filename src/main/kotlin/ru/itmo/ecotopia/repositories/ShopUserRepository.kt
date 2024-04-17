package ru.itmo.ecotopia.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itmo.ecotopia.model.dbEntities.ShopUserEntity
import java.util.UUID

@Repository
interface ShopUserRepository: JpaRepository<ShopUserEntity, UUID> {
    fun findByUsername(username: String): ShopUserEntity?
}