package test.yakovenko.domain.exception

import java.util.UUID

class ModelDeleteException(modelName: String, modelId: UUID) :
    RuntimeException("Failed to delete $modelName with id $modelId")
