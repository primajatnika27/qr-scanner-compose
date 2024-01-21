package com.prima.paymentqris.data.data_sources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prima.paymentqris.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrx(transaction: Transaction)

    @Query("SELECT * FROM transaction_tb")
    fun getAllTrx(): Flow<List<Transaction>>
}