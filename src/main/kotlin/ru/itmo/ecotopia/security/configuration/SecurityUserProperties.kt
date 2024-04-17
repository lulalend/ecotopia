package ru.itmo.ecotopia.security.configuration

import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.itmo.ecotopia.model.enums.UserPermission
import ru.itmo.ecotopia.model.enums.UserRole

@ConfigurationProperties("security")
data class SecurityUserProperties (
    val adminPermissionName: UserRole,
    val adminUsers: List<String>,
    val permissions: Map<UserRole, List<UserPermission>>,
)