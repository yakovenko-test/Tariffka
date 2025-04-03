package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Option
import kotlinx.coroutines.flow.Flow

interface OptionRepository {
    suspend fun create(option: Option)

    suspend fun readById(optionId: IdType): Option?
    suspend fun readAll(): Flow<List<Option>>

    suspend fun update(option: Option)

    suspend fun deleteById(optionId: IdType)
}
