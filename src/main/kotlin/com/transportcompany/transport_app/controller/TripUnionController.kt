package com.transportcompany.transport_app.controller


import com.transportcompany.transport_app.dto.TripUnionRequest
import com.transportcompany.transport_app.dto.TripUnionResponse
import com.transportcompany.transport_app.service.TripUnionService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
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
    fun createTrip(@RequestBody @Valid request: TripUnionRequest): ResponseEntity<TripUnionResponse> {
        val response = tripUnionService.createTrip(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PutMapping("/{id}")
    fun updateTrip(
        @PathVariable id: Long,
        @RequestBody @Valid request: TripUnionRequest
    ): ResponseEntity<TripUnionResponse> {
        val response = tripUnionService.updateTrip(id, request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getTripById(@PathVariable id: Long): ResponseEntity<TripUnionResponse> {
        val response = tripUnionService.getTripUnionById(id)
        return ResponseEntity.ok(response)
    }
}
