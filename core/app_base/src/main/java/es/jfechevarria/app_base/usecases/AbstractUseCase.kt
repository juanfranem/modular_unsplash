package es.jfechevarria.app_base.usecases

import es.jfechevarria.domain.result.AppResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

abstract class AbstractUseCase<Params, Result> {
    abstract suspend fun execute(params: Params): AppResult<Result>
    suspend operator fun invoke(
        params: Params
    ): Flow<AppResult<Result>> = flow {
        emit(AppResult.Loading)
        val result = withContext(Dispatchers.IO) {
                execute(params)
            }
        emit(result)
    }
}