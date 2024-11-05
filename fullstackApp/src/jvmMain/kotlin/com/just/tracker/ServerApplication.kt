package com.just.tracker

import com.just.tracker.plugins.configureRouting
import configureCompression
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.kvision.remote.kvisionInit
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

// The type alias for marking this application as the server's one.
typealias ServerApplication = Application

fun main() {
    embeddedServer(
        factory = Netty,
        port = SERVER_PORT,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}


fun ServerApplication.module() {
    configureCompression()
    configureRouting()

    val module = module {
        factoryOf(::PingService)
    }

    kvisionInit(module)
}