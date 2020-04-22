package com.example.security.controller

import com.example.security.entities.FormUser
import com.example.security.entities.User
import com.example.security.entities.UserRepository
import org.springframework.web.bind.annotation.*
import java.text.Normalizer

@RestController
@RequestMapping("/signup")
class SignInController(private val repository: UserRepository) {
    @GetMapping
    fun findAll() = repository.findAll()

    @PostMapping
    fun signUp(@RequestBody req: FormUser): String {
        repository.save(req.getUser())
        return "post success"
    }
}