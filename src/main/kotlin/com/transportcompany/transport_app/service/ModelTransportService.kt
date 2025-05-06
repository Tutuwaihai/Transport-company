package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.ModelTransportDto
import com.transportcompany.transport_app.dto.mappers.ModelTransportMapper
import com.transportcompany.transport_app.repository.ModelTransportRepository
import org.springframework.stereotype.Service


@Service
class ModelTransportService(
    private val modelTransportRepository: ModelTransportRepository,
    private val modelTransportMapper: ModelTransportMapper
) {

    fun getAllTrailers(): List<ModelTransportDto> {
        val trailers = modelTransportRepository.findAllTrailers()
        return trailers.map {
            ModelTransportDto(
                id = it.id,
                model = it.model,
                tonnage = it.tonnage,
                volume = it.volume
            )
        }
    }
//    fun getAllTrailers(): List<ModelTransportDto> {
//        val trailers = modelTransportRepository.findAllTrailers()
//        return modelTransportMapper.toDtoList(trailers)
//    }
}