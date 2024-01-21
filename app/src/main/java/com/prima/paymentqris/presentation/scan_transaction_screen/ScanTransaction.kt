package com.prima.paymentqris.presentation.scan_transaction_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanTransaction(
    navHostController: NavHostController,
    viewModel: ScanTransactionViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Scan QR",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )
        },
        floatingActionButton = {
            Row {
                SmallFloatingActionButton(
                    modifier = Modifier.padding(bottom = 60.dp),
                    onClick = {
                        viewModel.startScanning()
                    },
                    shape = CircleShape,
                ) {
                    Icon(Icons.Filled.Search, "")
                }

                Spacer(modifier = Modifier.width(16.dp))

                ExtendedFloatingActionButton(
                    modifier = Modifier.padding(bottom = 60.dp),
                    onClick = {
                        viewModel.onEvent(
                            ScanTransactionEvent.SaveTransaction(
                                context,
                                navHostController
                            )
                        )
                    },
                    icon = { Icon(Icons.Filled.Add, "") },
                    text = { Text(text = "Save") },
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), end = 8.dp, start = 8.dp, bottom = 8.dp),
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.bankName.value.bank,
                onValueChange = {
                    viewModel.onEvent(ScanTransactionEvent.EnteredBankName(it))
                },
                placeholder = {
                    Text(
                        text = viewModel.bankName.value.hint,
                        modifier = Modifier.alpha(0.5f)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.idTransaction.value.idTransaction,
                onValueChange = {
                    viewModel.onEvent(ScanTransactionEvent.EnteredIdTransaction(it))
                },
                placeholder = {
                    Text(
                        text = viewModel.idTransaction.value.hint,
                        modifier = Modifier.alpha(0.5f)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.merchantName.value.merchantName,
                onValueChange = {
                    viewModel.onEvent(ScanTransactionEvent.EnteredMerchantName(it))
                },
                placeholder = {
                    Text(
                        text = viewModel.merchantName.value.hint,
                        modifier = Modifier.alpha(0.5f)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.nominal.value.nominalTransaction,
                onValueChange = {
                    viewModel.onEvent(ScanTransactionEvent.EnteredNominal(it))
                },
                placeholder = {
                    Text(
                        text = viewModel.nominal.value.hint,
                        modifier = Modifier.alpha(0.5f)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                singleLine = true
            )
        }
    }


}