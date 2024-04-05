package ru.itmo.ecotopia.model.jPAEntities

import jakarta.persistence.*
import org.springframework.beans.FatalBeanException
import ru.itmo.ecotopia.model.entities.ShopUser
import java.util.*

@Entity
@Table(name = "users")
data class ShopUserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    var name: String? = null,
    var surname: String? = null,
    @Column(unique = true)
    var email: String? = null,
) {
    fun toShopUser(): ShopUser = ShopUser (
        id = this.id ?: throw FatalBeanException("user's id is null"),
        name = this.name ?: throw FatalBeanException("user's name is null"),
        surname = this.surname ?: throw FatalBeanException("user's surname is null"),
        email = this.email ?: throw FatalBeanException("user's email is null"),
    )
}