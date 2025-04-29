package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.dto.CreateTripRequest
import com.transportcompany.transport_app.dto.UpdateTripRequest
import com.transportcompany.transport_app.model.TripUnion
import com.transportcompany.transport_app.service.TripUnionService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/trip-union")
class TripUnionController(
    private val tripUnionService: TripUnionService
) {
    @PostMapping
    fun createTripUnion(@RequestBody @Valid request: CreateTripRequest): ResponseEntity<TripUnion> {
        val createdTripUnion = tripUnionService.createTrip(request)
        return ResponseEntity.ok(createdTripUnion)
    }

    @PutMapping("/{id}")
    fun updateTripUnion(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateTripRequest
    ): ResponseEntity<TripUnion> {
        val updatedTripUnion = tripUnionService.updateTrip(request)
        return ResponseEntity.ok(updatedTripUnion)
    }

    @GetMapping("/{id}")
    fun getTripUnionById(@PathVariable id: Long): ResponseEntity<TripUnion> {
        val tripUnion = tripUnionService.getTripUnionById(id)
        return ResponseEntity.ok(tripUnion)
    }
}