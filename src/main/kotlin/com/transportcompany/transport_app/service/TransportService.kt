package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.TransportDto
import com.transportcompany.transport_app.repository.TransportRepository
import org.springframework.stereotype.Service

@Service
class TransportService(
    private val transportRepository: TransportRepository
) {
    fun getAllTransport(): List<TransportDto> {
        return transportRepository.findAllTransport().map { row ->
            TransportDto(
                id = (row["id"] as Number).toLong(),
                mark = row["mark"] as? String ?: "Без марки",
                licensePlate = row["licensePlate"] as? String ?: "Нет номера",
                tonnage = (row["tonnage"] as? Number)?.toDouble() ?: 0.0,
                cityTitle = row["cityTitle"] as? String ?: "Неизвестный город"
            )
        }
    }
}