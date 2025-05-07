package com.transportcompany.transport_app.service


import com.transportcompany.transport_app.dto.TransportDto
import com.transportcompany.transport_app.dto.mappers.TransportMapper
import com.transportcompany.transport_app.repository.TransportRepository
import org.springframework.stereotype.Service

@Service
class TransportService(
    private val transportRepository: TransportRepository,
    private val transportMapper: TransportMapper
) {
    fun getAllTransport(): List<TransportDto> {
        val transports = transportRepository.findAllTransport()
        return transports.map {
            TransportDto(
                id = it.id,
                mark = it.mark,
                licensePlate = it.licensePlate,
                tonnage = it.tonnage,
                cityTitle = it.city?.title ?: "нет города"
            )
        }
    }
//    fun getAllTransport(): List<TransportDto> {
//        val drivers = transportRepository.findAllTransport()
//        return transportMapper.toDtoList(drivers)
//    }
}