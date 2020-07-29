package com.example.memo.service

import com.example.memo.dto.MemoDto
import com.example.memo.exception.MemoNotFoundException
import com.example.memo.exception.UserNotFoundException
import com.example.memo.model.Memo
import com.example.memo.repository.memo.MemoRxRepository
import com.example.memo.repository.user.UserRxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MemoService @Autowired constructor(
        private val memoRxRepository: MemoRxRepository,
        private val userRxRepository: UserRxRepository
) {
    fun createMemo(createDto: MemoDto): Mono<Unit> {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails

        return userRxRepository.findByUsernameAndIsActiveIfExist(userDetails.username, true)
                .map {
                    Memo(
                            title = createDto.title,
                            content = createDto.content,
                            tag = createDto.tag,
                            isPublic = createDto.isPublic,
                            user = it
                    )
                }
                .flatMap { memoRxRepository.save(it) }
    }

    fun getOneByIdIsNotDeleted(memoId: Long): Mono<Memo> {
        return memoRxRepository.existsByIdAndIsDeleted(memoId, false)
                .flatMap {
                    if (!it) Mono.error(MemoNotFoundException())
                    else Mono.just(memoId)
                }
                .flatMap { memoRxRepository.getOne(it) }
    }

    fun findAllMemo() : Flux<Memo> {
        return memoRxRepository.findAll()
    }

//    fun findAllByUsername(username: String): Flux<Memo> {
//        return memoRxRepository.findAllByUsername(username)
//    }

    fun findAllByUsername(username: String): Flux<Memo> {
        return userRxRepository.findByUsernameAndIsActiveIfExist(username, true)
                .flatMapMany {
                    memoRxRepository.findAllByUserAndIsDeleted(it, false)
                }
    }

    fun findAllByTag(tag: String?): Flux<Memo> {
        return memoRxRepository.findAllByTag(tag)
    }
}