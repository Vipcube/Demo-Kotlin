package org.example.kotlin.ktor.controller

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import org.example.kotlin.ktor.config.DIConfig
import org.example.kotlin.ktor.dto.Book
import org.example.kotlin.ktor.service.BookService
import org.kodein.di.instance

/**
 * The controller of book.
 *
 * @author Brad Chen
 *
 */
class BookController {
    private val bookService by DIConfig.di.instance<BookService>()

    /**
     * Find all books.
     *
     * @param context ApplicationCall
     */
    suspend fun findAll( context : ApplicationCall ) {
        context.respond( bookService.getAllBooks() );
    }

    /**
     * Add book.
     *
     * @param context ApplicationCall
     */
    suspend fun addBook( context : ApplicationCall ) {
        val bookRequest = context.receive<Book>()
        bookService.addBook( bookRequest )
        context.respond( HttpStatusCode.Accepted )
    }

    /**
     * Delete book.
     *
     * @param context ApplicationCall
     */
    suspend fun deleteBook( context : ApplicationCall ){
        val bookId = context.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        bookService.deleteBook( bookId )
        context.respond( HttpStatusCode.OK )
    }
}