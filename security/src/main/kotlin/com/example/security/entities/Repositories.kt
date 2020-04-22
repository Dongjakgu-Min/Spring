package com.example.security.entities

import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long>
interface MemoRepository: CrudRepository<Memo, Long>