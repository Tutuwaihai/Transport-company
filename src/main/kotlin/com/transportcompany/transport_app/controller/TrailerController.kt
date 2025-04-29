package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.dto.TrailerDto
import com.transportcompany.transport_app.service.TrailerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/trailers")
class TrailerController(private val trailerService: TrailerService) {

    @GetMapping
    fun getTrailers(): ResponseEntity<List<TrailerDto>> {
        return ResponseEntity.ok(trailerService.getAllTrailers())
    }
}