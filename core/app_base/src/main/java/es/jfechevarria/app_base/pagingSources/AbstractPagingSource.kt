package es.jfechevarria.app_base.pagingSources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import es.jfechevarria.domain.common.exceptions.GenericAppException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

abstract class AbstractPagingSource<R: Any>: PagingSource<Int, R>() {

    abstract suspend fun call(page: Int): Flow<List<R>>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, R> {
        val nextPageNumber = (params.key ?: 1) + 1
        return try {
            val result = call(params.key ?: 1).firstOrNull() ?: throw GenericAppException()
            LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = if (result.isEmpty()) null else nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, R>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }



}