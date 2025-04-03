package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.TariffDiscountEntity
import code.yakovenko.tariffka.domain.model.utils.IdType

@Dao
interface TariffDiscountDao {
    @Insert
    suspend fun insert(tariffDiscountEntity: TariffDiscountEntity)

    @Query("SELECT * FROM tariff_discounts WHERE id = :tariffDiscountId")
    suspend fun selectById(tariffDiscountId: IdType): TariffDiscountEntity?

    @Query("SELECT * FROM tariff_discounts")
    suspend fun selectAll(): List<TariffDiscountEntity>

    @Update
    suspend fun update(tariffDiscountEntity: TariffDiscountEntity): Int

    @Query("DELETE FROM tariff_discounts WHERE id = :tariffDiscountId")
    suspend fun deleteById(tariffDiscountId: IdType): Int
}
