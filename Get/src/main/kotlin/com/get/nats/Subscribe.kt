package com.get.nats

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.get.model.UserGet
import io.nats.client.Nats
import java.time.Duration


fun main(args: Array<String>) {
    while (true) {
        try {
            val mapper = jacksonObjectMapper()

            Nats.connect("nats://localhost:4222").use { nc ->
                println("Waiting for a message...")

                var userGet = UserGet()
                val sub = nc.subscribe("subTest")
                nc.flush(Duration.ofSeconds(5))

                val msg = sub.nextMessage(Duration.ofHours(1))
                println("Received JSON ${msg.subject} ${String(msg.data)}")

                var transform = mapper.readValue<UserGet>(msg.data)
                println(transform)
            }
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
    }
}
