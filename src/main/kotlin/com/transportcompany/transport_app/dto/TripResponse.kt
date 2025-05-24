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
    val cityFrom: CityDto,
    val cityTo: CityDto,
    val wareHouseFrom: WarehouseDto,
    val wareHouseTo: WarehouseDto,
    val tripType: TripTypeDto,
    val dispatchDate: Date?,
    val arrivalDate: Date?,
    val expectedDate: Date?,
    val docNum: String?,
    val isTransit: Boolean,
    val state: String,
    val costs: BigDecimal,
    val isCityCosts: Boolean,
    val employeeId: Long?,
    val transportId: Long?,
    val cargoSeal: String?,
)
