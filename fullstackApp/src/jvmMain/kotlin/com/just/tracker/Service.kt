package com.just.tracker

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual class PingService : IPingService {

    override suspend fun ping(message: String): String {
        println(message)
        return message
    }
}