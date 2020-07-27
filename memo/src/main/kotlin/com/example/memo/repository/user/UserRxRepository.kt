package com.example.memo.repository.user

import com.example.memo.dto.UserDto
import com.example.memo.model.User
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRxRepository {
    fun save(user: User): Mono<Unit>

    fun existByUsername(username: String): Mono<Boolean>
    fun existsByUsernameAndIsActive(username: String, isActive: Boolean): Mono<Boolean>

    fun findByUsername(username: String): Mono<User>
    fun findByUsernameAndIsActive(username: String, isActive: Boolean): Mono<Boolean>
}