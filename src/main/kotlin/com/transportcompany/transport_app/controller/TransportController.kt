package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.dto.TransportDto
import com.transportcompany.transport_app.service.TransportService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transport")
class TransportController(
    private val transportService: TransportService
) {

    @GetMapping
    fun getAllTransport(): ResponseEntity<List<TransportDto>> {
        return ResponseEntity.ok(transportService.getAllTransport())
    }
}