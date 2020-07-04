package com.example.account.repository

import com.example.account.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Repository
class AccountRxRepositoryImpl @Autowired constructor(
        private val accountRepository: AccountRepository
) : AccountRxRepository {
    override fun save(account: Account): Mono<Unit> {
        return Mono.fromCallable { accountRepository.save(account) }
                .subscribeOn(Schedulers.elastic())
    }

    override fun getOne(accountId: Long): Mono<Account> {
        return Mono.fromCallable { accountRepository.getOne(accountId) }
                .subscribeOn(Schedulers.elastic())
    }

    override fun findAllByUsername(username: String): Flux<Account> {
        return Mono.fromCallable { accountRepository.findAllByUsername(username) }
                .subscribeOn(Schedulers.elastic())
                .flatMapMany { Flux.fromIterable(it) }
    }

    override fun findAllByPhoneNumber(phoneNumber: String): Flux<Account> {
        return Mono.fromCallable { accountRepository.findAllByPhoneNumber(phoneNumber) }
                .subscribeOn(Schedulers.elastic())
                .flatMapMany { Flux.fromIterable(it) }
    }

    override fun findAll(): Flux<Account> {
        return Mono.fromCallable { accountRepository.findAll() }
                .subscribeOn(Schedulers.elastic())
                .flatMapMany { Flux.fromIterable(it) }
    }
}