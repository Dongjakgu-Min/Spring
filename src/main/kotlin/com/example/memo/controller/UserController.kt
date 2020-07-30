package com.example.memo.controller

import com.example.memo.dto.UserDto
import com.example.memo.repository.user.UserRepository
import com.example.memo.repository.user.UserRxRepository
import com.example.memo.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UserController @Autowired constructor(
        private val userRxRepository: UserRxRepository,
        private val userRepository: UserRepository,
        private val authService: AuthService
) {
    @GetMapping("/username/exist/{username}")
    fun existUser(@PathVariable username: String) = userRxRepository.existByUsername(username)

    @PostMapping("/signup")
    fun signUp(@RequestBody userDto: UserDto) = authService.signUp(userDto)

    @GetMapping("/login")
    fun login(@RequestBody userDto: UserDto) = authService.login(userDto)

    @GetMapping("/findAll")
    fun findAll() = userRepository.findAll()
}