package com.transportcompany.transport_app.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class CreateTripRequest(
    val description: String?,
    val cityFromId: Long,
    val cityToId: Long,
    val warehouseFromId: Long,
    val warehouseToId: Long,
    val tripTypeId: Long,
    val dispatchDate: LocalDateTime?,
    val arrivalDate: LocalDateTime?,
    val expectedDate: LocalDateTime?,
    val docNum: String?,
    val isTransit: Boolean,
    val state: String?,
    val costs: BigDecimal?,
    val isCityCosts: Boolean,
    val employeeId: Long?,
    val transportId: Long?,
    val cargoSeal: String?
)