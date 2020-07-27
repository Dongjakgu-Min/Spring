package com.example.memo.repository.user

import com.example.memo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun save(user: User)

    fun existsByUsername(username: String): Boolean
    fun existsByUsernameAndIsActive(username: String, isActive: Boolean): Boolean

    fun findByUsername(username: String): User
    fun findByUsernameAndIsActive(username: String, isActive: Boolean): Boolean
}