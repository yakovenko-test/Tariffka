package test.yakovenko.domain.exception

import java.util.UUID

class ModelReadException(modelName: String, modelId: UUID) :
    RuntimeException("Failed to read $modelName with id $modelId")
