package com.prima.paymentqris.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_tb")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var merchantName: String,
    var nominal: Long,
    var createdDate: String
)
