package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.client.JSONPlaceHolderClient
import com.transportcompany.transport_app.dto.LoginRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(
    private val jsonClient: JSONPlaceHolderClient
) {

    @GetMapping("/posts")
    fun getAllPosts(): List<LoginRequest> = jsonClient.getPosts()

    @GetMapping("/posts/{id}")
    fun getPost(@PathVariable id: Long): LoginRequest = jsonClient.getPostById(id)
}
