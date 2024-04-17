package ru.itmo.ecotopia.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.itmo.ecotopia.model.entities.Product
import ru.itmo.ecotopia.model.entities.ProductEntity
import java.util.*

@Repository
interface ProductRepository: JpaRepository<ProductEntity, UUID>