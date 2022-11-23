package com.mindera.rocketscience.data.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mindera.rocketscience.domain.entities.Launch
import com.mindera.rocketscience.domain.entities.LaunchOrder
import com.mindera.rocketscience.domain.repositories.LaunchRepository

class LaunchPagingSource(
	private val launchRepository: LaunchRepository,
	private val year: Int?,
	private val success: Boolean?,
	private val order: LaunchOrder?
) : PagingSource<Int, Launch>() {

	companion object {
		private const val STARTING_PAGE_INDEX = 0
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Launch> {
		val position = params.key ?: STARTING_PAGE_INDEX

		return try {
			val response = launchRepository.getLaunches(year, success, params.loadSize, position, order)

			LoadResult.Page(
				data = response,
				prevKey = if (position == STARTING_PAGE_INDEX) null else position,
				nextKey = if (response.isEmpty()) null else position + 1
			)
		} catch (ex: Exception) {
			LoadResult.Error(ex)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, Launch>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			state.closestPageToPosition(anchorPosition)?.prevKey
		}
	}

}