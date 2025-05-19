package exception

import java.util.*

class ModelDuplicateException(modelName: String, modelId: UUID) :
    RuntimeException("$modelName with id $modelId already exists")
