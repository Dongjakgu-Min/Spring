package com.example.memo.repository.memo

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import com.example.memo.model.Memo
import com.example.memo.model.User

interface MemoRxRepository {
    fun save(memo: Memo): Mono<Unit>

    fun getOne(memoId: Long): Mono<Memo>
    fun getOneIsDeleted(memoId: Long): Mono<Memo>

    fun existsByIdAndIsDeleted(memoId: Long, isDeleted: Boolean): Mono<Boolean>

    fun findAll(): Flux<Memo>
    fun findAllByTag(tag: String?): Flux<Memo>
    fun findAllByUserAndIsDeleted(user: User, isDeleted: Boolean): Flux<Memo>
}