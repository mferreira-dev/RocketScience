package com.mindera.rocketscience.data

import com.mindera.rocketscience.domain.entities.Company
import com.mindera.rocketscience.domain.entities.Launch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {

	@GET("info")
	suspend fun getCompanyInfo(): Response<Company>

	@GET("launches")
	suspend fun getLaunches(
		@Query("launch_year") year: Int?,
		@Query("launch_success") success: Boolean?,
		@Query("limit") limit: Int,
		@Query("offset") offset: Int,
		@Query("order") order: String?,
	): List<Launch>

}