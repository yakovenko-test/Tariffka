package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.OptionDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.Option
import code.yakovenko.tariffka.domain.repository.OptionRepository
import javax.inject.Inject

class OptionRepositoryImpl @Inject constructor(
    private val optionDao: OptionDao
): OptionRepository {
    override suspend fun getAll(): List<Option> {
        return optionDao.getAll().map { it.toDomain() }
    }
}
