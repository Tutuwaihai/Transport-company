package com.transportcompany.transport_app.client

import com.transportcompany.transport_app.dto.LoginRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable



@FeignClient(name = "jsonPlaceholder", url = "https://jsonplaceholder.typicode.com/")
interface JSONPlaceHolderClient {

    @GetMapping("/posts")
    fun getPosts(): List<LoginRequest>

    @GetMapping("/posts/{postId}", produces = ["application/json"])
    fun getPostById(@PathVariable("postId") postId: Long): LoginRequest
}
