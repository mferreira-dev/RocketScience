package com.mindera.rocketscience.data.utils

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(private val context: Context) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		if (!isConnected()) throw NoConnectivityException
		return chain.proceed(chain.request())
	}

	private fun isConnected(): Boolean {
		val connectivityManager =
			context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

		val networkInfo = connectivityManager.activeNetworkInfo
		return networkInfo != null && networkInfo.isConnected
	}

}