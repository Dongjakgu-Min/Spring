package com.example.memo.service.user

import com.example.memo.dto.UserDto
import com.example.memo.dto.mapper.UserMapper
import com.example.memo.model.User
import com.example.memo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class MyUserService(
        @Autowired val userRepository: UserRepository,
        @Autowired val bCryptPasswordEncoder: BCryptPasswordEncoder
) : UserService {
    override fun register(userDto: UserDto): UserDto {
        userDto.password = bCryptPasswordEncoder.encode(userDto.password)
        userRepository.save(UserMapper.toEntity(userDto))

        return userDto
    }

    override fun findUserByUsername(username: String): UserDto {
        val user = userRepository.findByUsername(username)
        user?: throw EntityNotFoundException()

        return UserMapper.toDto(user)
    }

    override fun updateProfile(userDto: UserDto, userId: Long): UserDto {
        val updatedUser = User(userId, userDto.username, userDto.password, userDto.role)
        userRepository.save(updatedUser)

        return userDto
    }

}