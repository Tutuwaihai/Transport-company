package com.transportcompany.transport_app.dto.mappers

import com.transportcompany.transport_app.dto.ModelTransportDto
import com.transportcompany.transport_app.model.ModelTransport
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ModelTransportMapper {


    fun toDtoList(entities: List<ModelTransport>): List<ModelTransportDto>
}