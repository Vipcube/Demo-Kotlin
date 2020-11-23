package org.example.kotlin.ktor.config.route

import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.kotlin.ktor.config.DIConfig
import org.example.kotlin.ktor.controller.BookController
import org.example.kotlin.ktor.dto.Book
import org.example.kotlin.ktor.service.BookService
import org.kodein.di.instance

/**
 * The route setting of book related. <br/>
 * The pattern it's used controller layer like common software architecture.
 *
 *
 * @receiver Route
 */
fun Route.booksController() {
    val bookController by DIConfig.di.instance<BookController>()

    get( "/" ) {
        bookController.findAll( this.context )
    }

    post("/add") {
        val context = this.context
        withContext( Dispatchers.IO ) {
            bookController.addBook( context )
        }
    }

    delete("/delete/{id}") {
        bookController.deleteBook( this.context )
    }
}

/**
 * The route setting of book related. <br/>
 * The pattern it's sees route as controller.
 *
 * @receiver Route
 */
fun Route.booksRouteController() {
    val bookService by DIConfig.di.instance<BookService>()

    get( "/" ) {
        context.respond( bookService.getAllBooks() );
    }

    post("/add") {
        withContext( Dispatchers.IO ) {
            val bookRequest = context.receive<Book>()
            bookService.addBook(bookRequest)
            context.respond(HttpStatusCode.Accepted)
        }
    }

    delete("/delete/{id}") {
        val bookId = context.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        bookService.deleteBook( bookId )
        context.respond( HttpStatusCode.OK )
    }
}