package com.example.account.repository

import com.example.account.model.Account
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AccountRxRepository {
    fun save(account: Account) : Mono<Unit>
    fun getOne(accountId: Long): Mono<Account>

    fun findAllByUsername(username: String): Flux<Account>
    fun findAllByPhoneNumber(phoneNumber: String): Flux<Account>
    fun findAll(): Flux<Account>
}