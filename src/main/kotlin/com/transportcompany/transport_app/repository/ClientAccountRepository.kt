package com.transportcompany.transport_app.repository


import org.springframework.stereotype.Repository
import com.transportcompany.transport_app.dto.LoginRequest


@Repository
class ClientAccountRepository(
) {
    private val users = mutableSetOf(
        LoginRequest(
            email = "email-1@gmail.com",
            password = ("\$2a\$10\\\$G4NoF7F6O6Gyx.LR6Q9MeOe/F1Zb.KPJJ2u8MSgrY5wKnxYuAvZzu"),
        )
    )

    fun findByEmail(email: String): LoginRequest? =
        users
            .firstOrNull { it.email == email }
}