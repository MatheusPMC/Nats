package com.post.controller

import com.post.model.UserPost
import com.post.nats.Publish
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post


@Controller("/")
class UserController(private val nats: Publish) {

    @Post
    fun create(@Body userPost: UserPost): HttpResponse<Any> {
        var send = nats.publish(userPost)
        return HttpResponse.created(HttpStatus.CREATED).body(send)
    }
}