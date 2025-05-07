package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.RouteDto
import com.transportcompany.transport_app.dto.mappers.RouteMapper
import com.transportcompany.transport_app.repository.RouteRepository
import org.springframework.stereotype.Service

@Service
class RouteService(
    private val routeRepository: RouteRepository,
    private val routeMapper: RouteMapper
) {

    fun filterRoutesByCityIds(fromCityId: Long?, toCityId: Long?): List<RouteDto> {
        val routes = routeRepository.findAllByIsDeleted(0).filter { route ->
            val fromMatches = fromCityId?.let { it == route.idCityFrom } ?: true
            val toMatches = toCityId?.let { it == route.idCityTo } ?: true
            fromMatches && toMatches
        }
        return routeMapper.toDtoList(routes)
    }

    fun getAllRoutes(): List<RouteDto> {
        val routes = routeRepository.findAllByIsDeleted(0)
        return routeMapper.toDtoList(routes)
    }
}