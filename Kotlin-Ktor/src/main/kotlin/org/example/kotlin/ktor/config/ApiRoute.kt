package org.example.kotlin.ktor.config

import io.ktor.routing.*
import org.example.kotlin.ktor.config.route.booksController

/**
 * The root config of api routing.
 *
 * @receiver Routing
 */
fun Routing.apiRoute() {
    route("/api/v1" ) {
        // Book
        route("/book" ) {
            booksController()
        }
    }
}