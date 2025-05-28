package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.dto.ModelTransportDto
import com.transportcompany.transport_app.service.ModelTransportService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/trailers")
class TrailerController(private val trailerService: ModelTransportService) {

    @GetMapping
    fun getTrailers() = trailerService.getAllTrailers()
}