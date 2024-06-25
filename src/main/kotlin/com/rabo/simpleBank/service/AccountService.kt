package com.rabo.simpleBank.service

import com.rabo.simpleBank.client.GetAccountDetailsHttpClient
import com.rabo.simpleBank.client.dto.Account
import com.rabo.simpleBank.exception.AccountDetailsException
import com.rabo.simpleBank.exception.InsufficientBalanceException
import com.rabo.simpleBank.exception.NoCardFoundException
import com.rabo.simpleBank.exception.NoMandateException
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service(value = "accountService")
class AccountService(
    private val accountDetailsHttpClient: GetAccountDetailsHttpClient,
) {
    private val log = LoggerFactory.getLogger(AccountService::class.java)

    fun getBalance(accountNumber: String): Double? {
        return try {
            fetchAccount(accountNumber).balance
        } catch (e: Exception) {
            throw AccountDetailsException("Unable to fetch ${accountNumber} from the account details API", e)
        }
    }

    fun withdraw(
        fromAccount: String,
        toAccount: String,
        amount: Double
    ): Boolean {
        val account = fetchAccount(fromAccount)
        checkPreConditions(account, amount)
        return try {
            val transactionCharges = if(account.creditCard != null) amount * 1.01 else amount
            accountDetailsHttpClient.withdraw(fromAccount, toAccount, transactionCharges)
            true
        } catch(e: Exception) {
            throw AccountDetailsException("Unable to withdraw from $fromAccount to $toAccount", e)
        }
    }

    fun deposit(
        fromAccount: String,
        toAccount: String,
        amount: Double
    ) {
        try {
            accountDetailsHttpClient.deposit(fromAccount, toAccount, amount)
        } catch(e: Exception) {
            throw AccountDetailsException("Unable to deposit from $fromAccount to $toAccount", e)
        }
    }

    private fun checkPreConditions(fromAccount: Account, amount: Double) {
        fromAccount.let {
            validateMandate(it.number, it.isMandatePresent)
            validateAmount(it, amount)
            validateCard(it)
        }
    }
    private fun validateMandate(fromAccount: String, isMandatePresent: Boolean) {
        if (!isMandatePresent) {
            // request direct debit contract
            if (!accountDetailsHttpClient.allowMandate(fromAccount)) {
                throw NoMandateException(fromAccount)
            }
        }
    }

    private fun fetchAccount(fromAccount: String) = accountDetailsHttpClient.getAccountDetails(fromAccount).let {
        it
    }

    private fun validateAmount(account: Account, amount: Double) {
        if(account.balance < amount) throw InsufficientBalanceException(account.number)
    }

    private fun validateCard(account: Account) {
        if(account.debitCard == null && account.creditCard == null) throw NoCardFoundException("No card details present in the account")
    }
}