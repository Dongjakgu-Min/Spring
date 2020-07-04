package com.example.memo.repository

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import com.example.memo.model.Memo

interface MemoRxRepository {
    fun save(memo: Memo): Mono<Unit>

    fun getOne(memoId: Long): Mono<Memo>

    fun findAll(): Flux<Memo>
    fun findAllByUsername(username: String): Flux<Memo>
    fun findAllByTag(tag: String?): Flux<Memo>
}