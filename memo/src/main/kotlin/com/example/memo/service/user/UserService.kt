package com.example.memo.service.user

import com.example.memo.dto.UserDto

interface UserService {
    fun register(userDto: UserDto): UserDto
    fun findUserByUsername(username: String): UserDto
    fun updateProfile(userDto: UserDto, userId: Long): UserDto
}