package com.example.account.dto

import com.example.account.model.Account
import java.util.*

class AccountDto(
        val id: Long?,
        val username: String,
        val phoneNumber: String
) {
    constructor(account: Account): this(
            id = account.id,
            username = account.username,
            phoneNumber = account.phoneNumber
    )

    fun toAccount() : Account {
        return Account(id, username, phoneNumber, Date(System.currentTimeMillis()))
    }
}