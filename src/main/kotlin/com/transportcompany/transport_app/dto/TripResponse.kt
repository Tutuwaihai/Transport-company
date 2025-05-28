package com.transportcompany.transport_app.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class TripResponse(
    val id: Long,
    val createDate: LocalDateTime?,
    val modifyDate: LocalDateTime?,
    val idFirmCustomer: Long,
    val idFirmCarrier: Long,
    val idRoute: Long,
    val idEmployee: Long,
    val idTransport: Long,
    val idTrailer: Long?,
    val costs: BigDecimal?,
    val description: String?,
    val isActive: Int
)