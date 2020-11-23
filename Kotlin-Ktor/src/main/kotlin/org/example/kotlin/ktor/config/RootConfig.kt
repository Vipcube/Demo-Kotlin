package org.example.kotlin.ktor.config

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.routing.*
import org.slf4j.event.Level

/**
 * The root config setting of application modules.
 *
 * @receiver Application
 * @param testing Boolean
 */
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install( CallLogging ) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    initDB()

    install( Routing ) {
        apiRoute()
    }
}