package com.rabo.simpleBank.model

import lombok.Data
import javax.persistence.Entity
import javax.persistence.Table

data class BalanceResponse(
    val account: String,
    val balance: Double
)

