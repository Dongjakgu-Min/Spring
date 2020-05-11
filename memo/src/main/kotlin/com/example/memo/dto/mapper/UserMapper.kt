package com.example.memo.dto.mapper

import com.example.memo.dto.UserDto
import com.example.memo.model.User

class UserMapper {
    companion object {
        fun toDto(user: User): UserDto {
            return UserDto(
                    user.id,
                    user.username,
                    user.password,
                    user.role
            )
        }

        fun toEntity(userDto: UserDto): User {
            return User(
                    userDto.id,
                    userDto.username,
                    userDto.password,
                    userDto.role
            )
        }
    }
}