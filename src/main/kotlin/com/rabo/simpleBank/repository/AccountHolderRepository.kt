package com.rabo.simpleBank.repository

import com.rabo.simpleBank.model.AccountHolder
import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository

@Repository
interface AccountHolderRepository : CrudRepository<AccountHolder?, Long?> {
    fun findByHolderName(username: String?): AccountHolder?
}
