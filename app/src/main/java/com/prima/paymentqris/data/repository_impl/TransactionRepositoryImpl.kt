package com.prima.paymentqris.data.repository_impl

import com.prima.paymentqris.data.data_sources.local.TransactionDao
import com.prima.paymentqris.domain.model.Transaction
import com.prima.paymentqris.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val dao: TransactionDao
): TransactionRepository {

    override fun insertTrx(transaction: Transaction) {
        dao.insertTrx(transaction)
    }

    override fun getAllTrx(): Flow<List<Transaction>> {
        return dao.getAllTrx()
    }
}