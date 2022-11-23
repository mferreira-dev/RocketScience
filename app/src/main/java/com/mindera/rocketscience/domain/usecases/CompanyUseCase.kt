package com.mindera.rocketscience.domain.usecases

import com.mindera.rocketscience.data.utils.Either
import com.mindera.rocketscience.data.utils.Failure
import com.mindera.rocketscience.domain.entities.Company
import com.mindera.rocketscience.domain.repositories.CompanyRepository
import javax.inject.Inject

class CompanyUseCase
@Inject
constructor(private val companyRepository: CompanyRepository) : BaseUseCase() {

	suspend fun getCompanyInfo(): Either<Company, Failure> {
		return try {
			httpHandler(companyRepository.getCompanyInfo())
		} catch (ex: Exception) {
			// i.e. Malformed JSON response.
			Either.Failure(Failure.ServerError)
		}
	}

}