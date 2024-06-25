package com.rabo.simpleBank.client.dto

data class Account(
    val number: String,
    val holderName: String,
    val password: String,
    val creditCard: Card.CreditCard? = null,
    val debitCard: Card.DebitCard? = null,
    val balance: Double = 0.0,
    /*By default, mandate between creditor and debtor for withdrawing an amount is not present
    so, it has to be requested first */
    val isMandatePresent: Boolean = false
)

sealed class Card(
    open val accountNumber: String,
    open val number: String,
    open val cvv: Int,
    open val expiryDate: String,
) {
    class CreditCard(
        override val accountNumber: String,
        override val number: String,
        override val cvv: Int,
        override val expiryDate: String,
    ) : Card(accountNumber, number, cvv, expiryDate)

    class DebitCard(
        override val accountNumber: String,
        override val number: String,
        override val cvv: Int,
        override val expiryDate: String,
    ): Card(accountNumber, number, cvv, expiryDate)
}

