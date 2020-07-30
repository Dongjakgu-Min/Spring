package com.example.memo.repository.memo

import com.example.memo.exception.MemoNotFoundException
import com.example.memo.model.Memo
import com.example.memo.model.User
import com.example.memo.repository.memo.MemoRepository
import com.example.memo.repository.memo.MemoRxRepository
import org.springframework.beans.factory.annotation.Autowired
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

    override fun getOneIsDeleted(memoId: Long): Mono<Memo> {
        return Mono.fromCallable { memoRepository.existsByIdAndIsDeleted(memoId, false) }
                .flatMap {
                    if (it) getOne(memoId)
                    else Mono.error(MemoNotFoundException())
                }
    }

    override fun findAll(): Flux<Memo> {
        return Mono.fromCallable { memoRepository.findAll() }
                .subscribeOn(Schedulers.elastic())
                .flatMapMany { Flux.fromIterable(it) }
    }

    override fun findAllByTag(tag: String?): Flux<Memo> {
        return Mono.fromCallable { memoRepository.findAllByTag(tag) }
                .subscribeOn(Schedulers.elastic())
                .flatMapMany { Flux.fromIterable(it) }
    }

    override fun findAllByUserAndIsDeleted(user: User, isDeleted: Boolean): Flux<Memo> {
        return Mono.fromCallable { memoRepository.findAllByUserAndIsDeleted(user, isDeleted) }
                .subscribeOn(Schedulers.elastic())
                .flatMapMany { Flux.fromIterable(it) }
    }

    override fun existsByIdAndIsDeleted(memoId: Long, isDeleted: Boolean): Mono<Boolean> {
        return Mono.fromCallable { memoRepository.existsByIdAndIsDeleted(memoId, isDeleted) }
                .subscribeOn(Schedulers.elastic())
    }
}