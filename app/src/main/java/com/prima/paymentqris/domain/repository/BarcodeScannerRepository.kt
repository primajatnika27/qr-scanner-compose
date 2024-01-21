package com.prima.paymentqris.domain.repository

import kotlinx.coroutines.flow.Flow


interface BarcodeScannerRepository {
    fun startScanning(): Flow<String?>
}