package org.example.kotlin.ktor.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import io.ktor.util.*
import org.example.kotlin.ktor.entity.Books
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

const val HIKARI_CONFIG_KEY = "ktor.hikariconfig"

/**
 * Init the database.
 *
 * @receiver Application
 */
@KtorExperimentalAPI
fun Application.initDB() {
    val configPath = environment.config.property( HIKARI_CONFIG_KEY ).getString()
    val dbConfig = HikariConfig( configPath )
    val dataSource = HikariDataSource( dbConfig )

    LoggerFactory.getLogger(Application::class.simpleName).info("Start to initialize Database")
    Database.connect( dataSource )
    createTables()
    LoggerFactory.getLogger(Application::class.simpleName).info("Finished to initialize Database")
}

/**
 * Create the database tables.
 */
private fun createTables() = transaction {
    SchemaUtils.create(
        Books
    )
    Books.insert {
        it[title] = "book1"
        it[author] = "author1"
    }
    Books.insert {
        it[title] = "book2"
        it[author] = "author2"
    }
}