package com.example.memo.repository.user

import com.example.memo.model.User
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRxRepository {
    fun existByUsername(username: String): Mono<Boolean>
    fun findByUsername(username: String): Mono<User>
}