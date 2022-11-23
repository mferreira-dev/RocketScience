package com.mindera.rocketscience.domain.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Launch(
	@Json(name = "flight_number")
	val flightNumber: Int,
	@Json(name = "mission_name")
	val name: String,
	@Json(name = "launch_date_unix")
	val date: Long,
	@Json(name = "launch_success")
	val launchSuccess: Boolean?,
	val rocket: Rocket,
	val links: Links
) {
	@JsonClass(generateAdapter = true)
	data class Rocket(
		@Json(name = "rocket_name")
		val name: String
	)

	@JsonClass(generateAdapter = true)
	data class Links(
		@Json(name = "mission_patch")
		val url: String?,
		val wikipedia: String?,
		@Json(name = "video_link")
		val video: String?,
		@Json(name = "article_link")
		val article: String?
	)
}