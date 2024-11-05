package com.just.tracker

class JsPlatform: Platform {
    override val name: String = "ES2015"
}

actual fun getPlatform(): Platform = JsPlatform()