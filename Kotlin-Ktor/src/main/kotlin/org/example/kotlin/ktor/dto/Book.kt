package org.example.kotlin.ktor.dto

/**
 * The DTO of book.
 *
 * @author Brad Chen
 *
 */
data class Book(
    val id: Int,
    val title: String,
    val author: String
)