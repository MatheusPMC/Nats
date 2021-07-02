package com.post.nats

import com.post.model.UserPost
import io.micronaut.nats.annotation.NatsClient
import io.micronaut.nats.annotation.Subject

@NatsClient
interface Publish {
    @Subject("subTest")
    fun publish(userPost: UserPost)
}