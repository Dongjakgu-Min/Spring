package com.example.memo.controller

import com.example.memo.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController @Autowired constructor(
        private val adminService: AdminService
) {
    @GetMapping("/{username}/signout")
    fun signOut(@PathVariable username: String) = adminService.forceSignOut(username)

    @GetMapping("/{username}/toadmin")
    fun toAdmin(@PathVariable username: String) = adminService.toAdmin(username)

    @GetMapping("/{username}/touser")
    fun toUser(@PathVariable username: String) = adminService.toUser(username)
}