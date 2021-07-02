package com.post.nats

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.post.model.UserPost
import io.nats.client.Nats
import java.time.Duration

fun main(args: Array<String>) {
    val mapper = jacksonObjectMapper()

    var userPost = UserPost("test","Terra Roxa")
    try {
        Nats.connect("nats://localhost:4222").use { nc ->
            println("About to publish...")

            nc.publish("subTest", mapper.writeValueAsBytes(userPost))
            nc.flush(Duration.ofSeconds(5))

            println(userPost)
            println("Done.")
        }
    } catch (exp: Exception) {
        exp.printStackTrace()
    }
}