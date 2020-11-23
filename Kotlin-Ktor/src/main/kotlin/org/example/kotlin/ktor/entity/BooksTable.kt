package org.example.kotlin.ktor.entity

import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * The table of book.
 *
 * @author Brad Chen
 *
 */
object Books : IntIdTable() {
    val title = varchar("title", 255)
    val author = varchar("author", 255)
}