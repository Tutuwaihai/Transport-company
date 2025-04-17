package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.repository.ClientAccountRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class JwtUserDetailsService(
    private val userRepository: ClientAccountRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("User $username not found!")

        return User.builder()
            .username(user.email)
            .password(user.password)
            .build()
    }
}