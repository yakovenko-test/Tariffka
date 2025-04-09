package code.yakovenko.tariffka.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import code.yakovenko.tariffka.data.local.AppDatabase
import code.yakovenko.tariffka.presentation.theme.TariffkaTheme

class MainActivity : ComponentActivity() {
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // appDatabase = AppDatabase.getDatabaseBuilder(this).build()

        enableEdgeToEdge()

        setContent {
            TariffkaTheme {

            }
        }
    }
}