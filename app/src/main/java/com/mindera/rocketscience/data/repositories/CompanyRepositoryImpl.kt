package com.mindera.rocketscience.data.repositories

import com.mindera.rocketscience.data.Endpoints
import com.mindera.rocketscience.domain.entities.Company
import com.mindera.rocketscience.domain.repositories.CompanyRepository
import retrofit2.Response
import javax.inject.Inject

class CompanyRepositoryImpl
@Inject
constructor(private val endpoints: Endpoints) : CompanyRepository {

	override suspend fun getCompanyInfo(): Response<Company> {
		return endpoints.getCompanyInfo()
	}

}