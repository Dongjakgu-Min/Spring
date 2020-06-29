package com.example.account.repository

import com.example.account.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*
import kotlin.collections.ArrayList

interface AccountRepository : JpaRepository<Account, Long> {
    fun save(account: Account)

    fun findAllByUsername(username: String): List<Account>
    fun findAllByPhoneNumber(phoneNumber: String): List<Account>
}