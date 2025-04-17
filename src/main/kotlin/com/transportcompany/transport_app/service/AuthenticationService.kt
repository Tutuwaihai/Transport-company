package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.AuthResponse
import com.transportcompany.transport_app.dto.LoginRequest
import com.transportcompany.transport_app.model.ClientAccount
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import com.transportcompany.transport_app.service.JwtService


@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: JwtUserDetailsService,
    private val tokenService: JwtService,
    @Value("\${jwt.accessTokenExpiration}") private val accessTokenExpiration: Long = 0,
) {
    fun authenticate(email: String, password: String): ClientAccount {
        val authToken = UsernamePasswordAuthenticationToken(email, password)
        authManager.authenticate(authToken)
    }

        val user = userDetailsService.loadUserByUsername(authenticationRequest.email)

        val accessToken = createAccessToken(user)


        return AuthResponse(
            accessToken = accessToken,
        )
    }

    private fun createAccessToken(user: UserDetails): String {
        return tokenService.generateToken(
            subject = user.username,
            expiration = Date(System.currentTimeMillis() + accessTokenExpiration)
        )
    }

}