package code.yakovenko.tariffka.domain.exception

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class UserNotFoundException(userId: Uuid) : RuntimeException("User with id $userId not found")
