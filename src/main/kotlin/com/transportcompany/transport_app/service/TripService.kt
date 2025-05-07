package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.CreateTripRequest
import com.transportcompany.transport_app.dto.TripResponse
import com.transportcompany.transport_app.dto.mappers.TripMapper
import com.transportcompany.transport_app.repository.*
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TripService(
    private val tripRepository: TripRepository,
    private val tripMapper: TripMapper
) {

    fun createTrip(request: CreateTripRequest): TripResponse {
        val entity = tripMapper.toEntity(request).copy(
            createDate = LocalDateTime.now(),
            isDeleted = 0
        )
        return tripMapper.toResponse(tripRepository.save(entity))
    }

    fun updateTrip(id: Long, request: CreateTripRequest): TripResponse {
        val entity = tripRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Рейс с id=$id не найден") }

        tripMapper.updateTripFromRequest(request, entity)
        entity.modifyDate = LocalDateTime.now()

        return tripMapper.toResponse(tripRepository.save(entity))
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