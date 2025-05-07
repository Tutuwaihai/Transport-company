package com.transportcompany.transport_app.dto

data class TransportDto(
    var id: Long,
    var mark: String?,
    var licensePlate: String?,
    var tonnage: Double?,
    var cityTitle: String?
)