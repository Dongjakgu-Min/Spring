package com.example.memo.repository.user

import com.example.memo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun save(user: User)

    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): User
}