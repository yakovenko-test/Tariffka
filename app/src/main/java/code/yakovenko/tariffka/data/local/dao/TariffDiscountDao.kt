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
    suspend fun insertTariffDiscount(tariffDiscountEntity: TariffDiscountEntity)

    @Query("SELECT * FROM tariff_discounts WHERE id = :tariffDiscountId")
    fun selectTariffDiscountById(tariffDiscountId: Int): Flow<TariffDiscountEntity?>

    @Query(
        """
        SELECT * FROM tariff_discounts td
        JOIN tariffs t ON td.tariff_id = t.id
        WHERE t.operator_id = :operatorId
    """
    )
    fun selectTariffDiscountsByOperatorId(operatorId: Int): Flow<List<TariffDiscountEntity>>

    @Query("SELECT * FROM tariff_discounts")
    fun selectAllTariffDiscounts(): Flow<List<TariffDiscountEntity>>

    @Update
    suspend fun updateTariffDiscount(tariffDiscountEntity: TariffDiscountEntity)

    @Query("DELETE FROM tariff_discounts WHERE id = :tariffDiscountId")
    suspend fun deleteTariffDiscountById(tariffDiscountId: Int)
}
