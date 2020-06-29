package com.example.account.service

import com.example.account.dto.AccountDto
import com.example.account.model.Account
import com.example.account.repository.AccountRepository
import com.example.account.repository.AccountRxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AccountService @Autowired constructor(
        private val repository: AccountRxRepository
) {
    fun createAccount(account: AccountDto): Mono<Unit> = repository.save(account.toAccount())
    fun getOne(accountId: Long) : Mono<Account> = repository.getOne(accountId)
    fun findAllByUsername(username: String): Flux<Account> = repository.findAllByUsername(username)
    fun findAllByPhoneNumber(phoneNumber: String): Flux<Account> = repository.findAllByPhoneNumber(phoneNumber)
}