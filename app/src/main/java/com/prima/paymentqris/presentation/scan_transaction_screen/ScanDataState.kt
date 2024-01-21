package com.prima.paymentqris.presentation.scan_transaction_screen

data class ScanDataState(
    val bank: String = "",
    val idTransaction: String = "",
    val merchantName: String = "",
    val nominalTransaction: String = "",
    val hint: String = ""
)