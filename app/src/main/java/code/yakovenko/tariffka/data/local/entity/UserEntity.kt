package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.MOCKED_USER

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Long,
)

fun UserEntity.toDomain() = MOCKED_USER
