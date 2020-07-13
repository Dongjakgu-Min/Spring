package com.example.memo.controller

import com.example.memo.repository.user.UserRxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController @Autowired constructor(
        private val userRxRepository: UserRxRepository
) {
    @GetMapping("/username/exist/{username}")
    fun existUser(@PathVariable username: String) = userRxRepository.existByUsername(username)
}