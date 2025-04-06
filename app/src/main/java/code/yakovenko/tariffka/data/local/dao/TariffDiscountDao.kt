package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.TariffDiscountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TariffDiscountDao {
    @Insert
    suspend fun insert(tariffDiscountEntity: TariffDiscountEntity)

    @Query("SELECT * FROM tariff_discounts WHERE id = :tariffDiscountId")
    suspend fun selectById(tariffDiscountId: Int): TariffDiscountEntity?

    @Query("SELECT * FROM tariff_discounts")
    fun selectAll(): Flow<List<TariffDiscountEntity>>

    @Update
    suspend fun update(tariffDiscountEntity: TariffDiscountEntity): Int

    @Query("DELETE FROM tariff_discounts WHERE id = :tariffDiscountId")
    suspend fun deleteById(tariffDiscountId: Int): Int
}
