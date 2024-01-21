package com.prima.paymentqris.domain.repository

import com.prima.paymentqris.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun insertTrx(transaction: Transaction)
    fun getAllTrx(): Flow<List<Transaction>>
}