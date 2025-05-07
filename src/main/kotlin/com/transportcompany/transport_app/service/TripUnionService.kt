package com.transportcompany.transport_app.service


import com.transportcompany.transport_app.dto.TripUnionRequest
import com.transportcompany.transport_app.dto.TripUnionResponse
import com.transportcompany.transport_app.dto.mappers.TripUnionMapper
import com.transportcompany.transport_app.repository.TripUnionRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TripUnionService(
    private val tripUnionRepository: TripUnionRepository,
    private val tripUnionMapper: TripUnionMapper
) {

    fun createTrip(request: TripUnionRequest): TripUnionResponse {
        val entity = tripUnionMapper.toEntity(request).copy(
            createDate = LocalDateTime.now(),
            isActive = 0,
            isDeleted = 0
        )
        val saved = tripUnionRepository.save(entity)
        return tripUnionMapper.toResponse(saved)
    }

    fun updateTrip(id: Long, request: TripUnionRequest): TripUnionResponse {
        val entity = tripUnionRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Путевой лист с id=$id не найден") }

        tripUnionMapper.updateTripUnionFromRequest(request, entity)
        entity.modifyDate = LocalDateTime.now()
        val updated = tripUnionRepository.save(entity)

        return tripUnionMapper.toResponse(updated)
    }

    fun getTripUnionById(id: Long): TripUnionResponse {
        val entity = tripUnionRepository.findById(id)
            .orElseThrow { NoSuchElementException("Путевой лист с id=$id не найден") }
        return tripUnionMapper.toResponse(entity)
    }
}
