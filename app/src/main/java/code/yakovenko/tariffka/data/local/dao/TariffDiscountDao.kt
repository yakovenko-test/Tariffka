package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import code.yakovenko.tariffka.data.local.entity.TariffDiscountEntity

@Dao
interface TariffDiscountDao {
    @Query("SELECT * FROM tariff_discounts")
    suspend fun getAll(): List<TariffDiscountEntity>
}
