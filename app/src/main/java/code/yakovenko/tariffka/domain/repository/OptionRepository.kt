package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Option

interface OptionRepository {
    suspend fun getAll(): List<Option>
}
