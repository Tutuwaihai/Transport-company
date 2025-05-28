package com.transportcompany.transport_app.service


import com.transportcompany.transport_app.dto.TripUnionRequest
import com.transportcompany.transport_app.dto.TripUnionResponse
import com.transportcompany.transport_app.dto.mappers.TripUnionMapper
import com.transportcompany.transport_app.repository.TripUnionRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class TripUnionService(
    private val tripUnionRepository: TripUnionRepository,
    private val tripUnionMapper: TripUnionMapper
) {

    fun createTrip(request: TripUnionRequest): TripUnionResponse {
        val entity = tripUnionMapper.toEntity(request)
        val saved = tripUnionRepository.save(entity)
        return tripUnionMapper.toResponse(saved)
    }

    fun updateTrip(id: Long, request: TripUnionRequest): TripUnionResponse {
        val entity = tripUnionRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Путевой лист с id=$id не найден") }

        val updated = tripUnionMapper.updateTripUnionFromRequest(request, entity)
        val saved = tripUnionRepository.save(updated)
        return tripUnionMapper.toResponse(saved)
    }

    fun getTripUnionById(id: Long): TripUnionResponse {
        val entity = tripUnionRepository.findById(id)
            .orElseThrow { NoSuchElementException("Путевой лист с id=$id не найден") }
        return tripUnionMapper.toResponse(entity)
    }
}
