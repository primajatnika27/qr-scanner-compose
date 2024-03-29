package com.prima.paymentqris.presentation.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TimeUtils {

    fun getFormattedTime(): String {
        val currentDateTime = Calendar.getInstance().time
        val formatter = SimpleDateFormat("MMM d, yyyy, hh:mm a", Locale.getDefault())
        return formatter.format(currentDateTime)

    }
}