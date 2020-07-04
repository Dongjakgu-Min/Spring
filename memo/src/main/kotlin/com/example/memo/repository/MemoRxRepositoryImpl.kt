package com.example.memo.repository

import com.example.memo.model.Memo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Schedules
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Repository
class MemoRxRepositoryImpl @Autowired constructor(
        private val memoRepository: MemoRepository
) : MemoRxRepository {
    override fun save(memo: Memo): Mono<Unit> {
        return Mono.fromCallable { memoRepository.save(memo) }
                .subscribeOn(Schedulers.elastic())
    }

    override fun getOne(memoId: Long): Mono<Memo> {
        return Mono.fromCallable { memoRepository.getOne(memoId) }
                .subscribeOn(Schedulers.elastic())
    }

    override fun findAll(): Flux<Memo> {
        return Mono.fromCallable { memoRepository.findAll() }
                .subscribeOn(Schedulers.elastic())
                .flatMapMany { Flux.fromIterable(it) }
    }

    override fun findAllByUsername(username: String): Flux<Memo> {
        return Mono.fromCallable { memoRepository.findAllByUsername(username) }
                .subscribeOn(Schedulers.elastic())
                .flatMapMany { Flux.fromIterable(it) }
    }

    override fun findAllByTag(tag: String?): Flux<Memo> {
        return Mono.fromCallable { memoRepository.findAllByTag(tag) }
                .subscribeOn(Schedulers.elastic())
                .flatMapMany { Flux.fromIterable(it) }
    }
}