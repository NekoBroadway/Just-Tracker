package com.just.tracker

import io.kvision.*
import io.kvision.html.Span
import io.kvision.html.h1
import io.kvision.html.span
import io.kvision.i18n.DefaultI18nManager
import io.kvision.i18n.I18n
import io.kvision.panel.root
import io.kvision.panel.simplePanel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

val AppScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
val greeting = Greeting()

class WebApplication : Application() {

    override fun start(state: Map<String, Any>) {
        I18n.manager =
            DefaultI18nManager(
                mapOf(
                    "en" to require("./i18n/messages-en.json"),
                    "pl" to require("./i18n/messages-pl.json")
                )
            )

        val root = root("kvapp") {
            simplePanel {
                h1(greeting.greet())
                span("Lorem ipsum")
                span("Dolor sit ammet")
            }
        }

        AppScope.launch {
            val pingResult = Model.ping(greeting.greet())
            root.add(Span(pingResult))
        }
    }

    override fun dispose(): Map<String, Any> {
        return mapOf()
    }
}

fun main() {
    startApplication(
        ::WebApplication,
        module.hot,
        BootstrapModule,
        BootstrapCssModule,
        FontAwesomeModule,
        MaterialModule,
        CoreModule
    )
}
