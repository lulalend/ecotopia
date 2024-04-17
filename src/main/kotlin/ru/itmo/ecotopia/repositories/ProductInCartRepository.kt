package ru.itmo.ecotopia.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itmo.ecotopia.model.dbEntities.ProductInCartEntity

import ru.itmo.ecotopia.model.ids.ProductCartId

@Repository
interface ProductInCartRepository: JpaRepository<ProductInCartEntity, ProductCartId>