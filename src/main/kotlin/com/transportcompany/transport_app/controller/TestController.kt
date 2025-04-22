package com.transportcompany.transport_app.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
class TestController {

    @GetMapping("/secured")
    fun securedEndpoint(authentication: Authentication): String {
        return "Вы авторизованы как: ${authentication.name} с ролями: ${authentication.authorities}"
    }
}
