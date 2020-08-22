package com.example.memo.controller

import com.example.memo.dto.UserDto
import com.example.memo.repository.user.UserRepository
import com.example.memo.repository.user.UserRxRepository
import com.example.memo.service.AuthService
import com.example.memo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user/")
class UserController @Autowired constructor(
        private val userRxRepository: UserRxRepository,
        private val userRepository: UserRepository,
        private val authService: AuthService
) {
    @GetMapping("/exist/{username}")
    fun existUser(@PathVariable username: String) = userRxRepository.existByUsername(username)

    @PostMapping("/signup")
    fun signUp(@RequestBody userDto: UserDto) = authService.signUp(userDto)

    @GetMapping("/login")
    fun login(@RequestBody userDto: UserDto) = authService.login(userDto)

    @GetMapping("/findAll")
    fun findAll() = userRepository.findAll()

    @GetMapping("/{username}/signout")
    fun signout(@PathVariable username: String) = authService.signOut(username)
}