package code.yakovenko.domain.model

import java.util.UUID

data class Operator(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val url: String?,
) {
    init {

    }
}
