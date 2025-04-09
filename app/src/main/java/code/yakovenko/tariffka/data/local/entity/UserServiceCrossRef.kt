package code.yakovenko.tariffka.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["user_id", "service_id"])
data class UserServiceCrossRef(
    @ColumnInfo("user_id")
    val userId: Int,
    @ColumnInfo("service_id")
    val serviceId: Int,
)
