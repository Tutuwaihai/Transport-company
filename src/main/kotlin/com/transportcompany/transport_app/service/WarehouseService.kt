package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.WarehouseDto
import com.transportcompany.transport_app.dto.mappers.WarehouseMapper
import com.transportcompany.transport_app.repository.WarehouseRepository
import org.springframework.stereotype.Service

@Service
class WarehouseService(
    private val wareHouseRepository: WarehouseRepository,
    private val wareHouseMapper: WarehouseMapper
) {
    fun getByCity(cityId: Long): List<WarehouseDto> =
        wareHouseMapper.toWareHouseDtoList(wareHouseRepository.findAllByCityId(cityId))
}