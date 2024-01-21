package com.prima.paymentqris.presentation.dashboard_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prima.paymentqris.domain.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
): ViewModel() {
    private val _balance = mutableStateOf(BalanceState())
    val balanceState: State<BalanceState> = _balance

    init {
        viewModelScope.launch {
            transactionRepository.getAllTrx().collect { trx ->
                _balance.value = balanceState.value.copy(
                    totalBalance = balanceState.value.originBalance!! - trx.sumOf { it.nominal }
                )
            }
        }
    }
}