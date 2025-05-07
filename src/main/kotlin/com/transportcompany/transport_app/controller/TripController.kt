package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.dto.CreateTripRequest
import com.transportcompany.transport_app.dto.TripResponse
import com.transportcompany.transport_app.service.TripService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/trips")
class TripController(
    private val tripService: TripService
) {

    @PostMapping
    fun createTrip(@RequestBody request: CreateTripRequest): TripResponse {
        return tripService.createTrip(request)
    }

    @PutMapping("/{id}")
    fun updateTrip(@PathVariable id: Long, @RequestBody request: CreateTripRequest): TripResponse {
        return tripService.updateTrip(id, request)
    }

    @GetMapping
    fun getAllTrips(): List<TripResponse> {
        return tripService.getAll()
    }

    @GetMapping("/{id}")
    fun getTripById(@PathVariable id: Long): TripResponse {
        return tripService.getById(id)
    }
}