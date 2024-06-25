package com.rabo.simpleBank.exception

internal class AccountDetailsException : RuntimeException {
    constructor() : super()
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}

internal class NoMandateException : RuntimeException {
    constructor() : super()
    constructor(accountDetails: String) : super("There is no legally bind mandate present to withdraw money from Debtor account ${accountDetails}")
}

internal class InsufficientBalanceException : RuntimeException {
    constructor() : super()
    constructor(accountDetails: String) : super("There is not enough balance to withdraw from debtor account ${accountDetails}")
}

internal class NoCardFoundException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
}


