package com.mindera.rocketscience.domain.repositories

import com.mindera.rocketscience.domain.entities.Launch
import com.mindera.rocketscience.domain.entities.LaunchOrder

interface LaunchRepository {
	suspend fun getLaunches(
		year: Int?,
		success: Boolean?,
		limit: Int,
		offset: Int,
		order: LaunchOrder?
	): List<Launch>
}