package com.prima.paymentqris.presentation.utils

import java.text.DecimalFormat
import java.text.NumberFormat

class CurrencyFormatter {

    fun priceValueToString(price: Long): String {

        val formatter: NumberFormat = DecimalFormat("#,###")
        return formatter.format(price)
    }

}