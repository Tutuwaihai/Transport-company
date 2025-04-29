package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.dto.EmployeeDto
import com.transportcompany.transport_app.service.EmployeeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drivers")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    @GetMapping
    fun getAllDrivers(): ResponseEntity<List<EmployeeDto>> {
        return ResponseEntity.ok(employeeService.getAllDrivers())
    }
}