package org.example.kotlin.ktor.entity

import org.example.kotlin.ktor.dto.Book
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * The book entity.
 *
 * @author Brad Chen
 *
 */
class BookEntity( id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BookEntity>(Books)

    var title by Books.title
    var author by Books.author

    override fun toString(): String = "Book($title, $author)"

    fun toBook() = Book( id.value, title, author )
}