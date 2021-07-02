package com.get.nats

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.get.model.UserGet

import io.micronaut.nats.annotation.NatsListener
import io.micronaut.nats.annotation.Subject

@NatsListener
class Subscribe() {
    val mapper = jacksonObjectMapper()

    @Subject("subTest")
    fun receive(userGet: UserGet) {
        var transformString = mapper.writeValueAsString(userGet)
        println("JSON:  $transformString")

        var transform = mapper.readValue<UserGet>(transformString)
        println(transform)
    }
}