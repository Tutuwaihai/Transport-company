package com.transportcompany.transport_app.dto.mappers

import org.mapstruct.Mapper
import com.transportcompany.transport_app.dto.TripUnionRequest
import com.transportcompany.transport_app.dto.TripUnionResponse
import com.transportcompany.transport_app.model.TripUnion
import org.mapstruct.BeanMapping
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring")
interface TripUnionMapper {

    fun toEntity(dto: TripUnionRequest): TripUnion

    fun toResponse(entity: TripUnion): TripUnionResponse

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun updateTripUnionFromRequest(dto: TripUnionRequest, @MappingTarget entity: TripUnion)
}
