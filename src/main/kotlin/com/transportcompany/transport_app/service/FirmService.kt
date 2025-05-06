package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.FirmDto
import com.transportcompany.transport_app.dto.mappers.FirmMapper
import com.transportcompany.transport_app.repository.FirmRepository
import org.springframework.stereotype.Service

@Service
class FirmService(
    private val firmRepository: FirmRepository,
    private val firmMapper: FirmMapper
) {
    fun getAllFirms(): List<FirmDto> {
        val firms = firmRepository.findAllByIsDeleted(0)
        return firmMapper.toDtoList(firms)
    }
}