package com.mindera.rocketscience.domain.usecases

import com.mindera.rocketscience.data.utils.Either
import com.mindera.rocketscience.data.utils.Failure
import retrofit2.Response

open class BaseUseCase {

	/**
	 * Example HTTP handler class. Handler is null by default in case no specific behavior is required.
	 *
	 * @param response The request's response.
	 * @param handler Custom handler for other HTTP codes.
	 *
	 * @return An either instance with the respective result.
	 */
	suspend fun <T> httpHandler(
		response: Response<T>,
		handler: (suspend (Response<T>) -> Either<T, Failure>)? = null
	): Either<T, Failure> {
		return when (response.code()) {
			in arrayOf(200, 201, 204, 206) -> Either.Success(response.body() ?: throw Exception())
			500 -> Either.Failure(Failure.ServerError)
			else -> handler?.invoke(response) ?: Either.Failure(Failure.ServerError)
		}
	}

}