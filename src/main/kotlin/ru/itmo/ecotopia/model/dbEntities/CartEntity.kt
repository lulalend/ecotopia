package ru.itmo.ecotopia.model.JPAentities

import jakarta.persistence.*
import org.springframework.beans.FatalBeanException
import ru.itmo.ecotopia.model.entities.Cart
import java.util.UUID

@Entity
@Table(name = "carts")
data class CartEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name = "user_id", nullable = false)
//    var userEntity: ShopUserEntity? = null,
) {
    fun toCart(): Cart = Cart (
        id = this.id ?: throw FatalBeanException("cart's id is null")
//        user = this.userEntity?.toShopUser() ?: throw FatalBeanException("cart's user is null"),
    )
}
