package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.exception.InvalidJwtTokenException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return filterChain.doFilter(request, response)
        }

        val token = authHeader.removePrefix("Bearer ").trim()
        try {
            jwtService.isTokenValid(token)
        } catch (e: InvalidJwtTokenException) {
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = "application/json"
            response.writer.write(
                """
                {
                  "code": 403,
                  "status": "FORBIDDEN",
                  "message": "${e.message}",
                  "data": null
                }
                """.trimIndent()
            )
            return
        }


    val username = jwtService.extractUsername(token)
    val authorities = jwtService.extractAuthorities(token)

    if (SecurityContextHolder.getContext().authentication == null) {
        val authToken = UsernamePasswordAuthenticationToken(username, null, authorities)
        SecurityContextHolder.getContext().authentication = authToken
    }

    filterChain.doFilter(request, response)
}
}