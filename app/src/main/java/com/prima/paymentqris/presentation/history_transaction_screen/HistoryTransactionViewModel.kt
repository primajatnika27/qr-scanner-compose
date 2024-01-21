package com.prima.paymentqris.presentation.history_transaction_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prima.paymentqris.domain.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryTransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
): ViewModel() {
    private val _listTransaction = mutableStateOf(HistoryTransactionState())

    val listTransaction: State<HistoryTransactionState> = _listTransaction

    init {
        viewModelScope.launch {
            transactionRepository.getAllTrx().collect {
                _listTransaction.value = listTransaction.value.copy(
                    listTransaction = it.reversed()
                )
            }
        }
    }
}