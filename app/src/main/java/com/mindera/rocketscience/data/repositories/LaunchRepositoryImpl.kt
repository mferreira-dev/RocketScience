package com.mindera.rocketscience.data.repositories

import com.mindera.rocketscience.data.Endpoints
import com.mindera.rocketscience.domain.entities.Launch
import com.mindera.rocketscience.domain.entities.LaunchOrder
import com.mindera.rocketscience.domain.repositories.LaunchRepository
import javax.inject.Inject

class LaunchRepositoryImpl
@Inject
constructor(private val endpoints: Endpoints) : LaunchRepository {

	override suspend fun getLaunches(
		year: Int?,
		success: Boolean?,
		limit: Int,
		offset: Int,
		order: LaunchOrder?
	): List<Launch> {
		return endpoints.getLaunches(year, success, limit, offset, order?.value)
	}

}