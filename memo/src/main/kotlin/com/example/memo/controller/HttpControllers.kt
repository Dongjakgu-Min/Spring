package com.example.memo.controller

import com.example.memo.database.Memo
import com.example.memo.database.MemoRepository
import com.example.memo.database.User
import com.example.memo.database.UserRepository
import org.aspectj.weaver.bcel.BcelAccessForInlineMunger
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(private val repository: UserRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAll()

    @PostMapping("/")
    fun signIn(@RequestBody form: User): User {
        form.password = BCrypt.hashpw(form.password, BCrypt.gensalt())
        repository.save(form)
        return form
    }
}

@RestController
@RequestMapping("/memo")
class MemoController(private val memoRepository: MemoRepository, private val userRepository: UserRepository) {

    @GetMapping("/")
    fun findAll() = memoRepository.findAll()

    @PostMapping("/")
    fun writeMemo(@RequestBody memo: Memo): Memo {
        memo.author = userRepository.findByUsername(SecurityContextHolder.getContext().authentication.principal.toString())!!
        return memo
    }
}