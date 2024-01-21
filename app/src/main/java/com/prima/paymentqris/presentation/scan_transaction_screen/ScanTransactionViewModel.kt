package com.prima.paymentqris.presentation.scan_transaction_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prima.paymentqris.domain.model.Transaction
import com.prima.paymentqris.domain.repository.BarcodeScannerRepository
import com.prima.paymentqris.domain.repository.TransactionRepository
import com.prima.paymentqris.presentation.utils.TimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanTransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val scannerRepository: BarcodeScannerRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _bankName = mutableStateOf(
        ScanDataState(
            hint = "Bank Name"
        )
    )
    val bankName: State<ScanDataState> = _bankName

    private val _idTransaction = mutableStateOf(
        ScanDataState(
            hint = "ID"
        )
    )
    val idTransaction: State<ScanDataState> = _idTransaction

    private val _merchantName = mutableStateOf(
        ScanDataState(
            hint = "Merchant Name"
        )
    )
    val merchantName: State<ScanDataState> = _merchantName

    private val _nominal = mutableStateOf(
        ScanDataState(
            hint = "Nominal Payment"
        )
    )
    val nominal: State<ScanDataState> = _nominal

    fun onEvent(event: ScanTransactionEvent) {
        when(event) {
            is ScanTransactionEvent.EnteredBankName -> {
                _bankName.value = bankName.value.copy(
                    bank = event.value
                )
            }

            is ScanTransactionEvent.EnteredIdTransaction -> {
                _idTransaction.value = idTransaction.value.copy(
                    idTransaction = event.value
                )
            }

            is ScanTransactionEvent.EnteredMerchantName -> {
                _merchantName.value = merchantName.value.copy(
                    merchantName = event.value
                )
            }

            is ScanTransactionEvent.EnteredNominal -> {
                _nominal.value = nominal.value.copy(
                    nominalTransaction = event.value
                )
            }

            is ScanTransactionEvent.SaveTransaction -> {
                if (_merchantName.value.merchantName != "" &&
                    _nominal.value.nominalTransaction != "" &&
                    _bankName.value.bank != "" &&
                    _idTransaction.value.idTransaction != "") {



                    CoroutineScope(Dispatchers.IO).launch {
                        transactionRepository.insertTrx(
                            Transaction(
                                id = 0,
                                merchantName = _merchantName.value.merchantName,
                                nominal = _nominal.value.nominalTransaction.toLong(),
                                createdDate = TimeUtils().getFormattedTime()
                            )
                        )
                    }

                    event.navHostController.navigateUp()
                } else {
                    Toast.makeText(
                        event.context,
                        "Attribute is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun startScanning() {
        viewModelScope.launch {
            scannerRepository.startScanning().collect {
                if (!it.isNullOrBlank()) {
                    Log.d("Barcode", "startScanning: $it")

                    val data = it.split("[.]".toRegex())

                    val (bank, id, merchant, nominalTransaction) = data

                    _bankName.value = bankName.value.copy(
                        bank = bank
                    )

                    _idTransaction.value = idTransaction.value.copy(
                        idTransaction = id
                    )

                    _merchantName.value = merchantName.value.copy(
                        merchantName = merchant
                    )

                    _nominal.value = nominal.value.copy(
                        nominalTransaction = nominalTransaction
                    )
                }
            }
        }
    }
}