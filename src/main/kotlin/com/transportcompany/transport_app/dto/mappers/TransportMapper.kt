package com.transportcompany.transport_app.dto.mappers

import com.transportcompany.transport_app.dto.TransportDto
import com.transportcompany.transport_app.model.Transport
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface TransportMapper {
    @Mapping(source = "city.title", target = "cityTitle")
    fun toDtoList(drivers: List<Transport>): List<TransportDto>
}