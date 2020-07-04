package com.example.account.controller

import com.example.account.dto.AccountDto
import com.example.account.repository.AccountRepository
import com.example.account.service.AccountService
import org.springframework.web.bind.annotation.*

@RestController
class AccountController (
        private val accountService: AccountService
) {
    @GetMapping("/")
    fun findAll() = accountService.findAll()

    @GetMapping("/username/{username}")
    fun findAllByUsername(@PathVariable username: String) = accountService.findAllByUsername(username)

    @PostMapping("/create")
    fun createAccount(@RequestBody account: AccountDto) = accountService.createAccount(account)

    @GetMapping("/phone/{phoneNumber}")
    fun findAllByPhoneNumber(@RequestBody phoneNumber: String) = accountService.findAllByPhoneNumber(phoneNumber)
}