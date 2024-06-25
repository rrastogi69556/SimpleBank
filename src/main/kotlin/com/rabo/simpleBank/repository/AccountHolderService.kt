package com.rabo.simpleBank.repository

import com.rabo.simpleBank.model.AccountHolder
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service("accountHolderService")
class AccountHolderService(
    private val accountHolderRepository: AccountHolderRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val accountHolder: AccountHolder =
            accountHolderRepository.findByHolderName(username) ?: throw UsernameNotFoundException("Invalid username or password.")
        return User(java.lang.String.valueOf(accountHolder.holderName), accountHolder.password, getAuthority())
    }

    private fun getAuthority(): List<SimpleGrantedAuthority> {
        return Arrays.asList(SimpleGrantedAuthority("ROLE_ADMIN"))
    }

    fun delete(id: Long) {
        accountHolderRepository.deleteById(id)
    }

    fun save(accountHolder: AccountHolder): AccountHolder {
        return accountHolderRepository.save(accountHolder)
    }

}