package code.yakovenko.tariffka.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserWithServices(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "service_id",
        associateBy = Junction(UserServiceCrossRef::class)
    )
    val services: List<ServiceEntity>
)
