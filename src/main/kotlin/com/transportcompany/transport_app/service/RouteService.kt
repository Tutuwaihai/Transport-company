package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.RouteDto
import com.transportcompany.transport_app.repository.RouteRepository
import org.springframework.stereotype.Service

@Service
class RouteService(
    private val routeRepository: RouteRepository
) {
    fun filterRoutes(fromCity: String?, toCity: String?, middleCity: String?): List<RouteDto> {
        val allRoutes = routeRepository.findAll()

        return allRoutes.filter { route ->
            val cities = route.title.split(",")
                .map { it.trim() }
                .map { city ->
                    city.substringBefore("-").trim()
                }

            val matchesFromCity = fromCity?.let {
                cities.firstOrNull()?.equals(it, ignoreCase = true)
            } ?: true

            val matchesToCity = toCity?.let {
                cities.lastOrNull()?.equals(it, ignoreCase = true)
            } ?: true

            val matchesMiddleCity = middleCity?.let {
                if (cities.size > 2) {
                    cities.drop(1).dropLast(1).any { it.equals(middleCity, ignoreCase = true) }
                } else {
                    false
                }
            } ?: true

            matchesFromCity && matchesToCity && matchesMiddleCity
        }.map { route ->
            RouteDto(
                id = route.id,
                title = route.title
            )
        }
    }

    fun getAllRoutes(): List<RouteDto> {
        return routeRepository.findAllActiveRoutes()
            .map { route ->
                RouteDto(
                    id = route.id,
                    title = route.title
                )
            }
    }
}