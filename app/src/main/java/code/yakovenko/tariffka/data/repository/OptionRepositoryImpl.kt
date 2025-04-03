package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.OptionDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.Option
import code.yakovenko.tariffka.domain.model.toData
import code.yakovenko.tariffka.domain.repository.OptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun readAll(): Flow<List<Option>> {
        return optionDao.selectAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun update(option: Option) {
        optionDao.update(option.toData())
    }

    override suspend fun deleteById(optionId: IdType) {
        optionDao.deleteById(optionId)
    }
}
