package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.dto.CreateTripRequest
import com.transportcompany.transport_app.dto.TripResponse
import com.transportcompany.transport_app.service.TripService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated


@Validated
@RestController
@RequestMapping("/trips")
class TripController(
    private val tripService: TripService
) {

    @PostMapping
    fun createTrip(@RequestBody @Valid request: CreateTripRequest): ResponseEntity<TripResponse> {
        val response = tripService.createTrip(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PutMapping("/{id}")
    fun updateTrip(@PathVariable id: Long, @RequestBody @Valid request: CreateTripRequest): TripResponse {
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