package com.prima.paymentqris.presentation.routes

sealed class Screen(val route: String) {
    object Dashboard: Screen(route = "dashboard")
    object HistoryTransaction: Screen(route = "history")
    object AddTransaction: Screen(route = "add_transaction")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            for (arg in args) {
                append("/$arg")
            }
        }
    }
}
