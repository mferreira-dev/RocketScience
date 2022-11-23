package com.mindera.rocketscience.data.utils

import java.io.IOException

object NoConnectivityException : IOException() {
	override val message: String = "NoConnectivityException"
}