package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.WarehouseDto
import com.transportcompany.transport_app.dto.mappers.WarehouseMapper
import com.transportcompany.transport_app.repository.WarehouseRepository
import org.springframework.stereotype.Service

@Service
class WarehouseService(
    private val wareHouseRepository: WarehouseRepository,
    private val warehouseMapper: WarehouseMapper
) {
    fun getByCity(cityId: Long): List<WarehouseDto> =
        warehouseMapper.toWareHouseDtoList(wareHouseRepository.findAllByCityId(cityId))
}