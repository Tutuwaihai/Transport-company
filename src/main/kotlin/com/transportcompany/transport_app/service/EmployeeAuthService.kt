package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.service.JwtService
import com.transportcompany.transport_app.dto.AuthResponse
import com.transportcompany.transport_app.dto.LoginRequest
import org.springframework.stereotype.Service

@Service
class EmployeeAuthService(private val authService: AuthService, private val jwtService: JwtService)
{
    fun login(request: LoginRequest, devToken: String): AuthResponse {
//        TODO("Сделать Jwt filter, будет совместно со security. Статус ответа: ОК. В итоге получить активного пользователя со всеми данными")

        val response = authService.login(request, devToken)
        val token = response.token

        val username = jwtService.extractUsername(token)
//        val authorities = jwtService.extractAuthorities(token)

//        val userDetails = User(
//            username,
//            "",
//            authorities
//        )

//        val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
//
//        SecurityContextHolder.getContext().authentication = authToken

        return response
    }
}