package com.prima.paymentqris.presentation.history_transaction_screen

import com.prima.paymentqris.domain.model.Transaction

data class HistoryTransactionState(
    val listTransaction: List<Transaction> = mutableListOf()
)