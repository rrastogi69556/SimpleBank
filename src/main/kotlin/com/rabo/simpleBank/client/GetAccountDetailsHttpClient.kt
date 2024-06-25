package com.rabo.simpleBank.client

import com.rabo.simpleBank.client.dto.Account
import com.rabo.simpleBank.client.dto.Card
import org.springframework.stereotype.Component

@Component
class GetAccountDetailsHttpClient {

    val accountDetails = listOf(
        Account(
            number = "NL12RABO0123456789",
            holderName = "John Smith",
            password = "public_pass",
            creditCard = null,
            debitCard = Card.DebitCard(
                number = "1234567891011121314",
                accountNumber = "NL12RABO0123456789",
                cvv = 123,
                expiryDate = "07/27"
            ),
            balance = "2700".toDouble(),
            isMandatePresent = false
        ),
        Account(
            number = "NL12RABO0123456790",
            holderName = "John Smith",
            debitCard = null,
            password = "public_pass",
            creditCard = Card.CreditCard(
                number = "1234567891011121315",
                accountNumber = "NL12RABO0123456790",
                cvv = 123,
                expiryDate = "07/27"
            ),
            balance = "3700".toDouble(),
            isMandatePresent = false
        )
    )
    /**
     * Assumed dependent API for fetching account related details, simulating random for debit or credit card presence
     */
    internal fun getAccountDetails(accountNumber: String) = accountDetails.filter {  it.number == accountNumber }.first()

    /**
     * Assumed dependent API for debiting amount account related details
     */
    internal fun withdraw(fromAccount: String,
                          toAccount: String,
                          amount: Double) = accountDetails.filter {  it.number == fromAccount }

    /**
     * Assumed dependent API for crediting amount account related details
     */
    internal fun deposit(fromAccount: String,
                          toAccount: String,
                          amount: Double) = accountDetails.filter {  it.number == toAccount }

    /**
     * Dummy API call for requesting direct debit contract between creditor and debitor
     */
    internal fun allowMandate(debtorAccountNumber: String): Boolean = listOf(true, false).random()
}