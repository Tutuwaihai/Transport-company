package com.transportcompany.transport_app.dto.mappers

import com.transportcompany.transport_app.dto.CityDto
import com.transportcompany.transport_app.dto.TripResponse
import com.transportcompany.transport_app.dto.TripTypeDto
import com.transportcompany.transport_app.dto.WarehouseDto
import com.transportcompany.transport_app.model.City
import com.transportcompany.transport_app.model.Trip
import com.transportcompany.transport_app.model.TripType
import com.transportcompany.transport_app.model.Warehouse
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface TripMapper {
    fun toResponse(trip: Trip): TripResponse

    fun toCityDto(city: City): CityDto
    fun toWarehouseDto(warehouse: Warehouse): WarehouseDto
    fun toTripTypeDto(tripType: TripType): TripTypeDto
}