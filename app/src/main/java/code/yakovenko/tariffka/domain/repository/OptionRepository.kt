package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Option
import code.yakovenko.tariffka.domain.model.utils.IdType

interface OptionRepository {
    suspend fun create(option: Option)

    suspend fun readById(optionId: IdType): Option?
    suspend fun readAll(): List<Option>

    suspend fun update(option: Option)

    suspend fun deleteById(optionId: IdType)
}
