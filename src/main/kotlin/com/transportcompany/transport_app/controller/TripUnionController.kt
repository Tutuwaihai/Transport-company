package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.dto.TripRequest
import com.transportcompany.transport_app.dto.TripResponse
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
   @ResponseStatus(HttpStatus.CREATED) 
    fun createTrip(
        @RequestBody @Valid request: TripRequest
    ): TripResponse = tripUnionService.createTrip(request)

    @PutMapping("/{id}") 
    fun updateTrip( 
        @PathVariable id: Long,
        @RequestBody @Valid request: TripRequest
    ): TripResponse = tripUnionService.updateTrip(id, request)

    @GetMapping("/{id}") 
    fun getTripById(
        @PathVariable id: Long
    ): TripResponse = tripUnionService.getTripUnionById(id)

}
