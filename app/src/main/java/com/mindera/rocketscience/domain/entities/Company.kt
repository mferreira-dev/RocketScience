package com.mindera.rocketscience.domain.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Company(
	val name: String,
	val founder: String,
	val founded: Int,
	val employees: Int,
	@Json(name = "launch_sites")
	val launchSites: Int,
	val valuation: Long
)