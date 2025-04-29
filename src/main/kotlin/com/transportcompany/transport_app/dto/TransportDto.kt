package com.transportcompany.transport_app.dto

data class TransportDto(
    val id: Long,
    val mark: String,
    val licensePlate: String,
    val tonnage: Double,
    val cityTitle: String
)