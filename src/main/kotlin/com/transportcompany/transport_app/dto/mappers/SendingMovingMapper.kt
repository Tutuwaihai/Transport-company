package com.transportcompany.transport_app.dto.mappers

import com.transportcompany.transport_app.dto.SendingMovingDto
import com.transportcompany.transport_app.model.SendingMoving
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface SendingMovingMapper {
    fun toDto (entity: SendingMoving): SendingMovingDto
    fun toDtoList(entities: List<SendingMoving>): List<SendingMovingDto>
}