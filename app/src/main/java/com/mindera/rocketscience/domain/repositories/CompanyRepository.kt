package com.mindera.rocketscience.domain.repositories

import com.mindera.rocketscience.domain.entities.Company
import retrofit2.Response

interface CompanyRepository {
	suspend fun getCompanyInfo(): Response<Company>
}