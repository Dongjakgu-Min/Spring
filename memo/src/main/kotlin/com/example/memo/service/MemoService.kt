package com.example.memo.service

import com.example.memo.dto.MemoDto
import com.example.memo.model.Memo
import com.example.memo.repository.MemoRxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MemoService @Autowired constructor(
        private val memoRxRepository: MemoRxRepository
) {
    fun createMemo(createDto: MemoDto): Mono<Unit> {
        return memoRxRepository.save(Memo(
                username = createDto.username,
                title = createDto.title,
                content = createDto.content,
                isPublic = createDto.isPublic,
                tag = createDto.tag
        ))
    }

    fun getOne(memoId: Long): Mono<Memo> {
        return memoRxRepository.getOne(memoId)
    }

    fun findAllMemo() : Flux<Memo> {
        return memoRxRepository.findAll()
    }

    fun findAllByUsername(username: String): Flux<Memo> {
        return memoRxRepository.findAllByUsername(username)
    }

    fun findAllByTag(tag: String?): Flux<Memo> {
        return memoRxRepository.findAllByTag(tag)
    }
}