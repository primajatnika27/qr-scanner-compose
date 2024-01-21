package com.prima.paymentqris.data.data_sources.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [com.prima.paymentqris.domain.model.Transaction::class],
    version = 1,
    exportSchema = false
)
abstract class TransactionDB: RoomDatabase() {
    abstract val transactionDao: TransactionDao

    companion object {
        const val DATABASE_NAME = "transaction_db"
    }
}