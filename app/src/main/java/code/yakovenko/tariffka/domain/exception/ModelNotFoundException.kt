package code.yakovenko.tariffka.domain.exception

import java.util.UUID

class ModelNotFoundException(modelName: String, modelId: UUID) :
    RuntimeException("$modelName with id $modelId not found")
