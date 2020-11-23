package org.example.kotlin.ktor

import io.ktor.http.*
import io.ktor.server.testing.*
import org.example.kotlin.ktor.config.module
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testBooks() {
        withTestApplication({ module(testing = true) }) {
            handleRequest( HttpMethod.Get, "/api/v1/book/" ).apply {
                assertEquals( HttpStatusCode.OK, response.status() )
                assertEquals("[{\"id\":1,\"title\":\"book1\",\"author\":\"author1\"},{\"id\":2,\"title\":\"book2\",\"author\":\"author2\"}]", response.content )
            }
        }
    }
}
