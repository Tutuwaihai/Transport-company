package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.CreateTripRequest
import com.transportcompany.transport_app.dto.TripResponse
import com.transportcompany.transport_app.dto.mappers.TripMapper
import com.transportcompany.transport_app.model.Trip
import com.transportcompany.transport_app.repository.*
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.Date

@Service
class TripService(
    private val tripRepository: TripRepository,
    private val cityRepository: CityRepository,
    private val warehouseRepository: WarehouseRepository,
    private val tripTypeRepository: TripTypeRepository,
    private val tripMapper: TripMapper
) {

    fun createTrip(request: CreateTripRequest): TripResponse {
        val cityFromId = requireNotNull(request.cityFromId) { "ID города отправления обязателен" }
        val cityToId = requireNotNull(request.cityToId) { "ID города прибытия обязателен" }
        val warehouseFromId = requireNotNull(request.warehouseFromId) { "ID склада отправления обязателен" }
        val warehouseToId = requireNotNull(request.warehouseToId) { "ID склада прибытия обязателен" }
        val tripTypeId = requireNotNull(request.tripTypeId) { "Тип рейса обязателен" }

        val cityFrom = cityRepository.getReferenceById(cityFromId)
        val cityTo = cityRepository.getReferenceById(cityToId)
        val wareHouseFrom = warehouseRepository.getReferenceById(warehouseFromId)
        val wareHouseTo = warehouseRepository.getReferenceById(warehouseToId)
        val tripType = tripTypeRepository.getReferenceById(tripTypeId)

        val trip = Trip(
            description = request.description,
            cityFrom = cityFrom,
            cityTo = cityTo,
            wareHouseFrom = wareHouseFrom,
            wareHouseTo = wareHouseTo,
            tripType = tripType,
            dispatchDate = request.dispatchDate,
            arrivalDate = request.arrivalDate,
            expectedDate = request.expectedDate,
            docNum = request.docNum ?: "",
            isTransit = request.isTransit,
            state = request.state?.toIntOrNull() ?: 0,
            costs = request.costs ?: BigDecimal.ZERO,
            isCityCosts = request.isCityCosts,
            idEmployee = request.employeeId,
            idTransport = request.transportId,
            cargoSeal = request.cargoSeal,
            createDate = LocalDateTime.now(),
            modifyDate = null,
            createUser = null,
            modifyUser = null,
            isDeleted = 0
        )
        return tripMapper.toResponse(tripRepository.save(trip))
    }

    fun updateTrip(id: Long, request: CreateTripRequest): TripResponse {
        val trip = tripRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Рейс с id=$id не найден") }

        val cityFromId = requireNotNull(request.cityFromId) { "ID города отправления обязателен" }
        val cityToId = requireNotNull(request.cityToId) { "ID города прибытия обязателен" }
        val warehouseFromId = requireNotNull(request.warehouseFromId) { "ID склада отправления обязателен" }
        val warehouseToId = requireNotNull(request.warehouseToId) { "ID склада прибытия обязателен" }
        val tripTypeId = requireNotNull(request.tripTypeId) { "Тип рейса обязателен" }

        trip.description = request.description
        trip.cityFrom = cityRepository.getReferenceById(cityFromId)
        trip.cityTo = cityRepository.getReferenceById(cityToId)
        trip.wareHouseFrom = warehouseRepository.getReferenceById(warehouseFromId)
        trip.wareHouseTo = warehouseRepository.getReferenceById(warehouseToId)
        trip.tripType = tripTypeRepository.getReferenceById(tripTypeId)
        trip.dispatchDate = request.dispatchDate
        trip.arrivalDate = request.arrivalDate
        trip.expectedDate = request.expectedDate
        trip.docNum = request.docNum ?: trip.docNum
        trip.isTransit = request.isTransit
        trip.state = request.state?.toIntOrNull() ?: trip.state
        trip.costs = request.costs ?: trip.costs
        trip.isCityCosts = request.isCityCosts
        trip.idEmployee = request.employeeId
        trip.idTransport = request.transportId
        trip.cargoSeal = request.cargoSeal
        trip.modifyDate = LocalDateTime.now()

        return tripMapper.toResponse(tripRepository.save(trip))
    }

    fun getAll(): List<TripResponse> {
        return tripRepository.findAllTrips().map(tripMapper::toResponse)
    }

    fun getById(id: Long): TripResponse {
        return tripRepository.findById(id)
            .map(tripMapper::toResponse)
            .orElseThrow { NoSuchElementException("Рейс с id=$id не найден") }
    }
}