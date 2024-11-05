package com.just.tracker.plugins

import com.just.tracker.Greeting
import com.just.tracker.PingService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.kvision.remote.applyRoutes
import io.kvision.remote.getAllServiceManagers

val greeting = Greeting()
val pingService = PingService()

fun Application.configureRouting() {
    routing {
        getAllServiceManagers().forEach { applyRoutes(it) }

        get("/") {
            call.respondText(pingService.ping(greeting.greet()))
        }
    }
}