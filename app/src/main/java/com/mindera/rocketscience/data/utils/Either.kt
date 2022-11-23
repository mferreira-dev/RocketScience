package com.mindera.rocketscience.data.utils

sealed class Either<out L, out R> {

	data class Success<L>(val data: L) : Either<L, Nothing>()
	data class Failure<R>(val exception: R) : Either<Nothing, R>()

	fun either(success: (L) -> Unit, failure: (R) -> Unit) {
		when (this) {
			is Success -> { success(data) }
			is Failure -> { failure(exception) }
		}
	}

}