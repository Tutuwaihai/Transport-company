package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.SendingMovingDto
import com.transportcompany.transport_app.dto.mappers.SendingMovingMapper
import com.transportcompany.transport_app.repository.SendingMovingRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class SendingMovingService(
    private val repository: SendingMovingRepository,
    private val sendingMovingMapper: SendingMovingMapper
) {
    fun getInvoicesInTrip(tripId: Long): List<SendingMovingDto> {
        return repository.findAllByIsDeletedAndTypePointerAndIdPointer(0, 2, tripId)
            .map(sendingMovingMapper::toDto)
    }

    fun getInvoicesInWarehouse(warehouseId: Long): List<SendingMovingDto> {
        return repository.findAllByIsDeletedAndTypePointerAndIdPointer(0, 1, warehouseId)
            .map(sendingMovingMapper::toDto)
    }

    @Transactional
    fun moveToTrip(sendingIds: List<Long>, tripId: Long) {
        val entities = repository.findAllById(sendingIds)
        if (entities.size != sendingIds.size) {
            throw EntityNotFoundException("Некоторые накладные не найдены")
        }

        entities.forEach {
            it.typePointer = 2
            it.idPointer = tripId
        }

        repository.saveAll(entities)
    }

    @Transactional
    fun moveToWarehouse(sendingIds: List<Long>, warehouseId: Long) {
        val entities = repository.findAllById(sendingIds)
        if (entities.size != sendingIds.size) {
            throw EntityNotFoundException("Некоторые накладные не найдены")
        }

        entities.forEach {
            it.typePointer = 1
            it.idPointer = warehouseId
        }

        repository.saveAll(entities)
    }

    @Transactional
    fun moveAllFromWarehouseToTrip(warehouseId: Long, tripId: Long) {
        val sendings = repository.findByTypePointerAndIdPointer(1, warehouseId)
        sendings.forEach {
            it.typePointer = 2
            it.idPointer = tripId
        }
        repository.saveAll(sendings)
    }

    @Transactional
    fun moveAllFromTripToWarehouse(tripId: Long, warehouseId: Long) {
        val sendings = repository.findByTypePointerAndIdPointer(2, tripId)
        sendings.forEach {
            it.typePointer = 1
            it.idPointer = warehouseId
        }
        repository.saveAll(sendings)
    }

    fun getFilteredSendingMovingsByWarehouse(warehouseId: Long, filter: String?): List<SendingMovingDto> {
        return when (filter?.uppercase()) {
            "ARRIVED" -> repository.findByTypePointerAndIdPointer(20, warehouseId)
            else -> repository.findByTypePointerAndIdPointer(1, warehouseId)
        }.map(sendingMovingMapper::toDto)
    }
}