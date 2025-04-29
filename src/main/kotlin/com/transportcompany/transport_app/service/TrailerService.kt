package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.TrailerDto
import com.transportcompany.transport_app.repository.TrailerRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class TrailerService(private val trailerRepository: TrailerRepository) {

    fun getAllTrailers(): List<TrailerDto> {
        return trailerRepository.findAllTrailers().map { row ->
            TrailerDto(
                id = (row["id"] as Number).toLong(),
                model = row["model"] as String,
                tonnage = row["tonnage"] as Double,
                volume = row["volume"] as Double
            )
        }
    }
}