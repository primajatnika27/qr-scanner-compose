package com.prima.paymentqris.data.repository_impl

import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.prima.paymentqris.domain.repository.BarcodeScannerRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BarcodeScannerRepositoryImpl(
    private val scanner: GmsBarcodeScanner
): BarcodeScannerRepository {

    override fun startScanning(): Flow<String?> {
        return callbackFlow {
            scanner.startScan()
                .addOnSuccessListener {
                    launch {
                        send(getDetailData(it))
                    }
                }.addOnFailureListener {
                    it.printStackTrace()
                }

            awaitClose { }
        }
    }

    private fun getDetailData(barcode: Barcode): String {
        return when(barcode.valueType) {
            Barcode.TYPE_TEXT -> {
                "text : ${barcode.rawValue}"
            }
            Barcode.TYPE_UNKNOWN -> {
                "unknown : ${barcode.rawValue}"
            }
            else -> {
                "Couldn't determine"
            }
        }
    }
}