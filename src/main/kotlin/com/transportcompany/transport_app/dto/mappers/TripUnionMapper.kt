package com.transportcompany.transport_app.dto.mappers

import com.transportcompany.transport_app.dto.TripRequest
import org.mapstruct.Mapper
import com.transportcompany.transport_app.dto.TripResponse
import com.transportcompany.transport_app.model.TripUnion
import org.mapstruct.BeanMapping
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring")
interface TripUnionMapper {

    fun toEntity(dto: TripRequest): TripUnion

    fun toResponse(entity: TripUnion): TripResponse

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun updateTripUnionFromRequest(dto: TripRequest, @MappingTarget entity: TripUnion)
}
