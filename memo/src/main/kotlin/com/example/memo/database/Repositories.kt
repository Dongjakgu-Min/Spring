package com.example.memo.database

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}

interface MemoRepository : CrudRepository<Memo, Long>
