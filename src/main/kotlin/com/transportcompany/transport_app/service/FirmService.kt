package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.FirmDto
import com.transportcompany.transport_app.repository.FirmRepository
import org.springframework.stereotype.Service

@Service
class FirmService(
    private val firmRepository: FirmRepository
) {
    fun getAllFirms(): List<FirmDto> {
        return firmRepository.findAllActiveFirms().map { firm ->
            FirmDto(
                id = firm.id,
                title = firm.title,
                phone = firm.phone
            )
        }
    }
}