package com.transportcompany.transport_app.dto.mappers

import com.transportcompany.transport_app.dto.CreateTripRequest
import com.transportcompany.transport_app.dto.TripResponse
import com.transportcompany.transport_app.model.Trip
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface TripMapper {

    fun toEntity(dto: CreateTripRequest): Trip

    fun toResponse(entity: Trip): TripResponse

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun updateTripFromRequest(dto: CreateTripRequest, @MappingTarget entity: Trip)
}