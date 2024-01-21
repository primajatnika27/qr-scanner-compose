package com.prima.paymentqris.presentation.scan_transaction_screen

import android.content.Context
import androidx.navigation.NavHostController

sealed class ScanTransactionEvent {
    data class EnteredBankName(val value: String): ScanTransactionEvent()
    data class EnteredIdTransaction(val value: String): ScanTransactionEvent()
    data class EnteredMerchantName(val value: String): ScanTransactionEvent()
    data class EnteredNominal(val value: String): ScanTransactionEvent()
    data class SaveTransaction(val context: Context, val navHostController: NavHostController): ScanTransactionEvent()
}