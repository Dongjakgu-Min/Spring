package com.example.security.controller

import com.example.security.entities.User
import com.example.security.entities.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/signup")
class SignInController(private val repository: UserRepository) {
    @GetMapping
    fun findAll() = repository.findAll()

    @PostMapping
    fun signUp(@RequestBody req: User): String {
        repository.save(req)
        return "post success"
    }
}