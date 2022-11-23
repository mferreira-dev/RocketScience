package com.mindera.rocketscience.data.utils

sealed class Failure {
	object NetworkConnection : Failure()
	object Unauthorized : Failure()
	object ServerError : Failure()
	abstract class FeatureFailure : Failure()
}