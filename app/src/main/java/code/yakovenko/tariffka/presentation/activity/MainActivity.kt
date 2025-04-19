package code.yakovenko.tariffka.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import code.yakovenko.tariffka.data.model.TariffFeedbackEntity
import code.yakovenko.tariffka.data.table.DiscountsTable
import code.yakovenko.tariffka.data.table.OperatorsTable
import code.yakovenko.tariffka.data.table.ServicesTable
import code.yakovenko.tariffka.data.table.SupportTicketsTable
import code.yakovenko.tariffka.data.table.TariffFeedbacksTable
import code.yakovenko.tariffka.data.table.TariffsTable
import code.yakovenko.tariffka.data.table.UsersTable
import code.yakovenko.tariffka.presentation.theme.TariffkaTheme
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            TariffkaTheme {

            }
        }
    }
}
