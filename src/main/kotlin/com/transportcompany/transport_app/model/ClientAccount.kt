package com.transportcompany.transport_app.model

import jakarta.persistence.*


@Entity
data class ClientAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val email: String,
    val password: String,
    val type: Int = 1,
    val isPhoneVerified: Boolean = false,
    val isEmailVerified: Boolean = true
)