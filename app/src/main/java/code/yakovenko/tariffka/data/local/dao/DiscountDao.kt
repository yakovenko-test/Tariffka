package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.DiscountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscountDao {
    @Insert
    suspend fun insertDiscount(discountEntity: DiscountEntity)

    @Query("SELECT * FROM discounts WHERE id = :discountId")
    fun selectDiscountById(discountId: Int): Flow<DiscountEntity?>

    @Query("SELECT * FROM discounts WHERE operator_id = :operatorId")
    fun selectDiscountsByOperatorId(operatorId: Int): Flow<List<DiscountEntity>>

    @Query("SELECT * FROM discounts")
    fun selectAllDiscounts(): Flow<List<DiscountEntity>>

    @Update
    suspend fun updateDiscount(discountEntity: DiscountEntity)

    @Query("DELETE FROM discounts WHERE id = :discountId")
    suspend fun deleteDiscountById(discountId: Int)
}
