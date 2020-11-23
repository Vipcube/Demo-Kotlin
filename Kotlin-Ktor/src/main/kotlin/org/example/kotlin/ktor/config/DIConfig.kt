package org.example.kotlin.ktor.config

import org.example.kotlin.ktor.controller.BookController
import org.example.kotlin.ktor.service.BookService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

/**
 * The config of dependence injection.
 *
 * @author Brad Chen
 *
 */
object DIConfig {
    /**
     * The module of book.
     */
    private val bookModule = DI.Module( "Book" ) {
        bind<BookController>() with singleton { BookController() }
        bind<BookService>() with singleton { BookService() }
    }

    /**
     * The root of dependence injection.
     */
    val di = DI {
        import( bookModule )
    }
}