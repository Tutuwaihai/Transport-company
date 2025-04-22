package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.dto.LoginRequest
import com.transportcompany.transport_app.service.EmployeeAuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: EmployeeAuthService
) {
    @PostMapping("/login")
    fun login(
        @RequestHeader("NrgApi-DevToken") devToken: String,
        @RequestBody request: LoginRequest
    ) =
        authService.login(request, devToken)

}