package com.transportcompany.transport_app.dto

import com.transportcompany.transport_app.model.City
import com.transportcompany.transport_app.model.TripType
import com.transportcompany.transport_app.model.Warehouse
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class TripResponse(
    val id: Long,
    val description: String?,
    val cityFrom: City?,
    val cityTo: City?,
    val warehouseFrom: Warehouse?,
    val warehouseTo: Warehouse?,
    val tripType: TripType,
    val dispatchDate: LocalDateTime?,
    val arrivalDate: LocalDateTime?,
    val expectedDate: LocalDateTime?,
    val docNum: String?,
    val isTransit: Boolean,
    val state: String?,
    val costs: BigDecimal?,
    val isCityCosts: Boolean,
    val employee: Long?,
    val transport: Long?,
    val cargoSeal: String?
)
