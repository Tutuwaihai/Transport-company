package com.transportcompany.transport_app.dto.mappers

import com.transportcompany.transport_app.dto.TransportDto
import com.transportcompany.transport_app.model.Transport
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TransportMapper {
    fun toDtoList(drivers: List<Transport>): List<TransportDto>
}