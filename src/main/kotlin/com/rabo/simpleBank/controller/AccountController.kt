package com.rabo.simpleBank.controller

import com.rabo.simpleBank.model.BalanceResponse
import com.rabo.simpleBank.service.AccountService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts", produces = [MediaType.APPLICATION_JSON_VALUE])
internal class AccountController(
    private val accountService: AccountService,
    ) {

    @GetMapping("/balance", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun balance(@RequestParam("accountNumber") accountNumber: String,
    ) = accountService.getBalance(accountNumber)?.let { BalanceResponse(account = accountNumber, balance = it) }

    @GetMapping("/withdraw")
    fun withdraw(
        @RequestParam("fromAccountNumber") fromAccountNumber: String,
        @RequestParam("toAccountNumber") toAccountNumber: String,
        @RequestParam("amount") amount: Double,
    ){
        accountService.withdraw(
            fromAccount = fromAccountNumber,
            toAccount = toAccountNumber,
            amount = amount
        )
    }

    @GetMapping("/deposit")
    fun deposit(
        @RequestParam("accountNumber") accountNumber: String,
        @RequestParam("amount") amount: Double,
    ){
        accountService.deposit(
            fromAccount = accountNumber,
            toAccount = accountNumber,
            amount = amount
        )
    }
}