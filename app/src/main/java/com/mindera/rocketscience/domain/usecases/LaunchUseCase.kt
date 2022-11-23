package com.mindera.rocketscience.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mindera.rocketscience.data.sources.LaunchPagingSource
import com.mindera.rocketscience.domain.entities.LaunchOrder
import com.mindera.rocketscience.domain.repositories.LaunchRepository
import javax.inject.Inject

class LaunchUseCase
@Inject
constructor(private val launchRepository: LaunchRepository) : BaseUseCase() {

	companion object {
		const val PAGE_SIZE = 20

		/**
		 * Start recycling at MAX_SIZE.
		 */
		const val MAX_SIZE = 100
	}

	fun getLaunches(
		year: Int? = null,
		success: Boolean? = null,
		order: LaunchOrder? = null
	) = Pager(
		config = PagingConfig(pageSize = PAGE_SIZE, maxSize = MAX_SIZE, enablePlaceholders = true),
		pagingSourceFactory = { LaunchPagingSource(launchRepository, year, success, order) }
	).liveData

}