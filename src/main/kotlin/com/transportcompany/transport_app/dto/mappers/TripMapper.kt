package com.transportcompany.transport_app.dto.mappers

import com.transportcompany.transport_app.dto.CityDto
import com.transportcompany.transport_app.dto.TripResponse
import com.transportcompany.transport_app.dto.TripTypeDto
import com.transportcompany.transport_app.dto.WarehouseDto
import com.transportcompany.transport_app.model.City
import com.transportcompany.transport_app.model.Trip
import com.transportcompany.transport_app.model.TripType
import com.transportcompany.transport_app.model.Warehouse
import com.transportcompany.transport_app.repository.CityRepository
import com.transportcompany.transport_app.repository.TripTypeRepository
import com.transportcompany.transport_app.repository.WarehouseRepository
import org.mapstruct.*
import org.springframework.beans.factory.annotation.Autowired
import com.transportcompany.transport_app.dto.CreateTripRequest
import java.math.BigDecimal
import java.time.LocalDateTime
import jakarta.persistence.EntityNotFoundException

@Mapper(componentModel = "spring")
abstract class TripMapper {
    @Autowired
    protected lateinit var cityRepository: CityRepository

    @Autowired
    protected lateinit var warehouseRepository: WarehouseRepository

    @Autowired
    protected lateinit var tripTypeRepository: TripTypeRepository

    fun toEntity(dto: CreateTripRequest, userId: Long): Trip {
        val cityFrom = dto.cityFromId?.let {
            cityRepository.findById(it).orElseThrow {
                EntityNotFoundException("Город отправления с id=$it не найден")
            }
        } ?: throw IllegalArgumentException("ID города отправления обязателен")

        val cityTo = dto.cityToId?.let {
            cityRepository.findById(it).orElseThrow {
                EntityNotFoundException("Город прибытия с id=$it не найден")
            }
        } ?: throw IllegalArgumentException("ID города прибытия обязателен")

        val wareHouseFrom = dto.warehouseFromId?.let {
            warehouseRepository.findById(it).orElseThrow {
                EntityNotFoundException("Склад отправления с id=$it не найден")
            }
        } ?: throw IllegalArgumentException("ID склада отправления обязателен")

        val wareHouseTo = dto.warehouseToId?.let {
            warehouseRepository.findById(it).orElseThrow {
                EntityNotFoundException("Склад прибытия с id=$it не найден")
            }
        } ?: throw IllegalArgumentException("ID склада прибытия обязателен")

        val tripType = dto.tripTypeId?.let {
            tripTypeRepository.findById(it).orElseThrow {
                EntityNotFoundException("Тип рейса с id=$it не найден")
            }
        } ?: throw IllegalArgumentException("Тип рейса обязателен")

        return Trip(
            description = dto.description,
            cityFrom = cityFrom,
            cityTo = cityTo,
            wareHouseFrom = wareHouseFrom,
            wareHouseTo = wareHouseTo,
            tripType = tripType,
            dispatchDate = dto.dispatchDate,
            arrivalDate = dto.arrivalDate,
            expectedDate = dto.expectedDate,
            docNum = dto.docNum ?: "",
            isTransit = dto.isTransit,
            state = dto.state?.toIntOrNull() ?: 0,
            costs = dto.costs ?: BigDecimal.ZERO,
            isCityCosts = dto.isCityCosts,
            idEmployee = dto.employeeId,
            idTransport = dto.transportId,
            cargoSeal = dto.cargoSeal,
            createDate = LocalDateTime.now(),
            modifyDate = null,
            createUser = userId,
            modifyUser = null,
            isDeleted = 0
        )
    }

    fun updateTripFromRequest(dto: CreateTripRequest, entity: Trip, userId: Long): Trip {
        val cityFrom = dto.cityFromId?.let {
            cityRepository.findById(it).orElseThrow {
                EntityNotFoundException("Город отправления с id=$it не найден")
            }
        } ?: entity.cityFrom

        val cityTo = dto.cityToId?.let {
            cityRepository.findById(it).orElseThrow {
                EntityNotFoundException("Город прибытия с id=$it не найден")
            }
        } ?: entity.cityTo

        val wareHouseFrom = dto.warehouseFromId?.let {
            warehouseRepository.findById(it).orElseThrow {
                EntityNotFoundException("Склад отправления с id=$it не найден")
            }
        } ?: entity.wareHouseFrom

        val wareHouseTo = dto.warehouseToId?.let {
            warehouseRepository.findById(it).orElseThrow {
                EntityNotFoundException("Склад прибытия с id=$it не найден")
            }
        } ?: entity.wareHouseTo

        val tripType = dto.tripTypeId?.let {
            tripTypeRepository.findById(it).orElseThrow {
                EntityNotFoundException("Тип рейса с id=$it не найден")
            }
        } ?: entity.tripType

        return entity.copy(
            description = dto.description ?: entity.description,
            cityFrom = cityFrom,
            cityTo = cityTo,
            wareHouseFrom = wareHouseFrom,
            wareHouseTo = wareHouseTo,
            tripType = tripType,
            dispatchDate = dto.dispatchDate ?: entity.dispatchDate,
            arrivalDate = dto.arrivalDate ?: entity.arrivalDate,
            expectedDate = dto.expectedDate ?: entity.expectedDate,
            docNum = dto.docNum ?: entity.docNum,
            isTransit = dto.isTransit,
            state = dto.state?.toIntOrNull() ?: entity.state,
            costs = dto.costs ?: entity.costs,
            isCityCosts = dto.isCityCosts,
            idEmployee = dto.employeeId,
            idTransport = dto.transportId,
            cargoSeal = dto.cargoSeal ?: entity.cargoSeal,
            modifyDate = LocalDateTime.now(),
            modifyUser = userId
        )
    }

    abstract fun toResponse(trip: Trip): TripResponse

}