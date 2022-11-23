package com.mindera.rocketscience.utils

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.mindera.rocketscience.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.empty(): String {
	return ""
}

fun convertMoney(value: Long): String {
	val format = NumberFormat.getCurrencyInstance().apply {
		maximumFractionDigits = 0
		currency = Currency.getInstance("USD")
	}

	return format.format(value)
}

fun convertTimestampToDays(value: Long): String {
	val now = System.currentTimeMillis()

	return if (now > value)
		"${((now / 1000 / 60 / 60 / 24) - (value / 60 / 60 / 24))} days ago"
	else
		"${((now / 1000 / 60 / 60 / 24) + (value / 60 / 60 / 24))} days left"
}

fun convertTimestampToDate(value: Long): String {
	val sdf = SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.US)
	val date = Date(value * 1000)
	return sdf.format(date)
}

fun displayToast(ctx: Context, message: String, length: Int = 0) {
	Toast.makeText(ctx, message, length).show()
}

fun getCircularProgressDrawable(ctx: Context): CircularProgressDrawable {
	return CircularProgressDrawable(ctx).apply {
		strokeWidth = 5f
		centerRadius = 30f
		setColorSchemeColors(ContextCompat.getColor(ctx, R.color.teal_200))
		start()
	}
}