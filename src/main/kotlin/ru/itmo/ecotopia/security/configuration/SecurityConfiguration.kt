package ru.itmo.ecotopia.security.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import ru.itmo.ecotopia.model.enums.UserPermission
import ru.itmo.ecotopia.security.managers.BasicUserAuthenticationManager
import ru.itmo.ecotopia.security.managers.BearerAuthenticationManager
import ru.itmo.ecotopia.service.TokenService


@Configuration
@EnableWebSecurity
class SecurityConfiguration {
    @Bean
    fun securityFilterChain(http: HttpSecurity,
                            basicAuthFilter: UsernamePasswordAuthenticationFilter,
                            bearerTokenAuthFilter: UsernamePasswordAuthenticationFilter,
    ) : SecurityFilterChain {
        http {
            authorizeRequests {
                authorize("/sign_up", permitAll)
                authorize("/api/sign_in", authenticated)
                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/products/**"), hasAuthority(UserPermission.ADD_PRODUCT.name))
                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/products/**"), hasAuthority(UserPermission.GET_PRODUCT.name))
                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/products/**"), hasAuthority(UserPermission.GET_PRODUCTS.name))
                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.PUT, "/api/products/**"), hasAuthority(UserPermission.EDIT_PRODUCT.name))
                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, "/api/products/**"), hasAuthority(UserPermission.DELETE_PRODUCT.name))
                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/carts/**"), hasAuthority(UserPermission.CART.name))
                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/carts/**"), hasAuthority(UserPermission.GET_CARTS.name))
                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, "/api/carts/**"), hasAuthority(UserPermission.CART.name))
                authorize( "/api/product_in_cart/**", hasAuthority(UserPermission.EDIT_PRODUCTS_IN_CART.name))
//                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/product_in_order/**"), hasAuthority(UserPermission.EDIT_PRODUCTS_IN_ORDER.name))
//                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/product_in_order/**"), hasAuthority(UserPermission.EDIT_PRODUCTS_IN_ORDER.name))
//                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.PUT, "/api/product_in_order/**"), hasAuthority(UserPermission.EDIT_PRODUCTS_IN_ORDER.name))
//                authorize(AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, "/api/product_in_order/**"), hasAuthority(UserPermission.EDIT_PRODUCTS_IN_ORDER.name))
            }

            cors {}
            csrf { disable() }

            sessionManagement{
                SessionCreationPolicy.STATELESS
            }

            addFilterBefore<UsernamePasswordAuthenticationFilter>(filter = basicAuthFilter)
            addFilterBefore<UsernamePasswordAuthenticationFilter>(filter = bearerTokenAuthFilter)
        }
        return http.build()
    }

    @Bean
    fun basicAuthFilter(authManager: AuthenticationManager): UsernamePasswordAuthenticationFilter {
        val filter = UsernamePasswordAuthenticationFilter()
        filter.setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/api/sign_in", "POST"))
        filter.setAuthenticationManager(authManager)
        filter.setAuthenticationFailureHandler(AuthenticationEntryPointFailureHandler(HttpStatusEntryPoint(HttpStatus.I_AM_A_TEAPOT)))
        return filter
    }

    @Bean
    fun bearerTokenAuthFilter(tokenService: TokenService)
    : UsernamePasswordAuthenticationFilter {
        val filter = UsernamePasswordAuthenticationFilter()
        filter.setAuthenticationManager(BearerAuthenticationManager(tokenService))
        filter.setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/api/**", "POST"))
        filter.setAuthenticationFailureHandler(AuthenticationEntryPointFailureHandler(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        return filter
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(4)
}