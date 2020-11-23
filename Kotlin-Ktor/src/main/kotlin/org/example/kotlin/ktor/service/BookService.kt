package org.example.kotlin.ktor.service

import org.example.kotlin.ktor.dto.Book
import org.example.kotlin.ktor.entity.BookEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

/**
 * The service of book.
 *
 * @author Brad Chen
 *
 */
class BookService {
    private val log = LoggerFactory.getLogger( BookService::class.simpleName )

    /**
     * Get all book.
     *
     * @return Iterable<Book>
     */
    fun getAllBooks(): Iterable<Book> = transaction {
        log.info( "BookService: Get all books." )
        BookEntity.all().map(BookEntity::toBook)
    }

    /**
     * Add book.
     *
     * @param book Book
     * @return BookEntity
     */
    fun addBook( book: Book ) = transaction {
        log.info( "BookService: Add book with {} {}.", book.title, book.author )
        BookEntity.new {
            this.title = book.title
            this.author = book.author
        }
    }

    /**
     * Delete book.
     *
     * @param bookId Int
     */
    fun deleteBook( bookId: Int ) = transaction {
        log.info( "BookService: Delete book with id {}.", bookId )
        BookEntity[bookId].delete()
    }
}