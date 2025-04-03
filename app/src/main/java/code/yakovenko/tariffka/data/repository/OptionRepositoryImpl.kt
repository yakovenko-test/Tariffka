package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.OptionDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.Option
import code.yakovenko.tariffka.domain.model.toData
import code.yakovenko.tariffka.domain.model.utils.IdType
import code.yakovenko.tariffka.domain.repository.OptionRepository
import javax.inject.Inject

class OptionRepositoryImpl @Inject constructor(
    private val optionDao: OptionDao
) : OptionRepository {
    override suspend fun create(option: Option) {
        optionDao.insert(option.toData())
    }

    override suspend fun readById(optionId: IdType): Option? {
        return optionDao.selectById(optionId)?.toDomain()
    }

    override suspend fun readAll(): List<Option> {
        return optionDao.selectAll().map { it.toDomain() }
    }

    override suspend fun update(option: Option) {
        optionDao.update(option.toData())
    }

    override suspend fun deleteById(optionId: IdType) {
        optionDao.deleteById(optionId)
    }
}
