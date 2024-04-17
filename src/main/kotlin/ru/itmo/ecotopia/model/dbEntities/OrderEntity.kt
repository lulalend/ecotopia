package ru.itmo.ecotopia.model.dbEntities

import jakarta.persistence.*
import org.springframework.beans.FatalBeanException
import ru.itmo.ecotopia.model.entities.Order
import ru.itmo.ecotopia.model.enums.StatusOrder
import java.time.Instant
import java.util.*

@Entity
@Table(name = "orders")
data class OrderEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    var date: Instant? = null,
    @Enumerated(EnumType.STRING)
    var status: StatusOrder ?= null,
    var totalCost: Double? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: ShopUserEntity? = null
) {
    fun toOrder(): Order = Order (
        id = this.id ?: throw FatalBeanException("order's id is null"),
        date = this.date ?: throw FatalBeanException("order's date is null"),
        status = this.status ?: throw FatalBeanException("order's status is null"),
        totalCost = this.totalCost ?: throw FatalBeanException("order's total cost is null"),
        user = this.user?.toShopUser() ?: throw FatalBeanException("cart's user is null"),
    )
}