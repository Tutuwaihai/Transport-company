package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.CityDto
import com.transportcompany.transport_app.repository.CityRepository
import org.springframework.stereotype.Service

@Service
class CityService(
    private val cityRepository: CityRepository
) {
    fun getAllCities(): List<CityDto> {
        return cityRepository.findAllCities().map { city ->
            CityDto(
                id = city.id,
                title = city.title
            )
        }
    }
}