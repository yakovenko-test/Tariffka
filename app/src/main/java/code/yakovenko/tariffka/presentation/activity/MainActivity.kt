package code.yakovenko.tariffka.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import code.yakovenko.tariffka.di.DaggerUseCaseComponent
import code.yakovenko.tariffka.presentation.theme.TariffkaTheme
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            TariffkaTheme {
                DaggerUseCaseComponent.create().getCreateUserUseCase()
            }
        }
    }
}
