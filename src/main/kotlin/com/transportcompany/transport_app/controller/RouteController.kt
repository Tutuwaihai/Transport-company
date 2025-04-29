package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.dto.RouteDto
import com.transportcompany.transport_app.service.RouteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/routes")
class RouteController(
    private val routeService: RouteService
) {

    @GetMapping
    fun getAllRoutes(): ResponseEntity<List<RouteDto>> {
        return ResponseEntity.ok(routeService.getAllRoutes())
    }

    @GetMapping("/filter")
    fun filterRoutes(
        @RequestParam(required = false) fromCity: String?,
        @RequestParam(required = false) toCity: String?,
        @RequestParam(required = false) middleCity: String?
    ): ResponseEntity<List<RouteDto>> {
        val routes = routeService.filterRoutes(fromCity, toCity, middleCity)
        return ResponseEntity.ok(routes)
    }
}
