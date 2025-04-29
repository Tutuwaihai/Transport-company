package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.CreateTripRequest
import com.transportcompany.transport_app.dto.UpdateTripRequest
import com.transportcompany.transport_app.model.TripUnion
import com.transportcompany.transport_app.repository.TripUnionRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TripUnionService(
    private val tripUnionRepository: TripUnionRepository,
) {

    fun createTrip(request: CreateTripRequest): TripUnion {
        val tripUnion = TripUnion(
            createDate = LocalDateTime.now(),
            idFirmCustomer = request.idFirmCustomer!!,
            idFirmCarrier = request.idFirmCarrier!!,
            idRoute = request.idRoute!!,
            idEmployee = request.idEmployee!!,
            idTransport = request.idTransport!!,
            idTrailer = request.idTrailer,
            costs = request.costs,
            description = request.description,
            isActive = 0
        )
        return tripUnionRepository.save(tripUnion)
    }

    fun updateTrip(request: UpdateTripRequest): TripUnion {
        val tripUnion = tripUnionRepository.findById(request.id)
            .orElseThrow { EntityNotFoundException("Путевой лист с id=${request.id} не найден") }

        tripUnion.modifyDate = LocalDateTime.now()
        tripUnion.idFirmCustomer = request.idFirmCustomer!!
        tripUnion.idFirmCarrier = request.idFirmCarrier!!
        tripUnion.idRoute = request.idRoute!!
        tripUnion.idEmployee = request.idEmployee!!
        tripUnion.idTransport = request.idTransport!!
        tripUnion.idTrailer = request.idTrailer
        tripUnion.costs = request.costs
        tripUnion.description = request.description

        return tripUnionRepository.save(tripUnion)
    }

    fun getTripUnionById(id: Long): TripUnion {
        return tripUnionRepository.findById(id)
            .orElseThrow { NoSuchElementException("Путевой лист с id=$id не найден") }
    }
}