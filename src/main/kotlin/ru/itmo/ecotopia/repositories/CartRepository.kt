package ru.itmo.ecotopia.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itmo.ecotopia.model.JPAentities.CartEntity
import java.util.UUID

@Repository
interface CartRepository: JpaRepository<CartEntity, UUID> {
}