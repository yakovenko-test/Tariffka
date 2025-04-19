package code.yakovenko.tariffka.integration.exposed

import code.yakovenko.tariffka.data.table.DiscountsTable
import code.yakovenko.tariffka.data.table.OperatorsTable
import code.yakovenko.tariffka.data.table.ServicesTable
import code.yakovenko.tariffka.data.table.SupportTicketsTable
import code.yakovenko.tariffka.data.table.TariffFeedbacksTable
import code.yakovenko.tariffka.data.table.TariffsTable
import code.yakovenko.tariffka.data.table.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction

internal object TestExposedDatabaseController {
    private const val URL = "jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;"
    private const val DRIVER = "org.h2.Driver"

    private val tables = arrayOf(
        DiscountsTable,
        OperatorsTable,
        ServicesTable,
        SupportTicketsTable,
        TariffFeedbacksTable,
        TariffsTable,
        UsersTable
    )

    val database: Database = Database.connect(
        url = URL,
        driver = DRIVER
    )

    fun setUp() = transaction(database) {
        SchemaUtils.create(*tables)
    }

    fun cleanUp() = transaction(database) {
        SchemaUtils.drop(*tables)
    }
}
