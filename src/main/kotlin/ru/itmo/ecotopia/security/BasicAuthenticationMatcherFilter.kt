package ru.itmo.ecotopia.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


class BasicAuthenticationMatcherFilter(
    authenticationManager: AuthenticationManager,
    private val pathMatchers: List<AntPathRequestMatcher>,
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        var matched = false
        val header = request.getHeader("Authorization")
        for (pathMatcher in pathMatchers) {
            if (pathMatcher.matcher(request).isMatch) matched = true
        }
        if (header == null && matched) {
            chain.doFilter(request, response)
        } else if (header == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value())
        } else if (header.contains("Basic") && matched) {
            super.doFilterInternal(request, response, chain)
        } else if (!matched && header.contains("Basic")) {
            response.sendError(HttpStatus.UNAUTHORIZED.value())
        } else {
            chain.doFilter(request, response)
        }
//        if (matched and header.contains("Basic")) {
//            super.doFilterInternal(request, response, chain)
//        } else if (header.contains("")) {
//            response.status = HttpStatus.UNAUTHORIZED.value()
//            response.sendError(HttpStatus.UNAUTHORIZED.value())
//            chain.doFilter(request, response)
//            return
    }

}