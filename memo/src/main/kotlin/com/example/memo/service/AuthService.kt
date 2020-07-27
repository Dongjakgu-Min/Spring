package com.example.memo.service

import com.example.memo.dto.UserDto
import com.example.memo.model.User
import com.example.memo.repository.user.UserRxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthService @Autowired constructor(
        private val userRxRepository: UserRxRepository,
        private val passwordEncoder: PasswordEncoder
) {
    fun signUp(userDto: UserDto): Mono<Unit> {
        return userRxRepository.existByUsername(userDto.username)
                .flatMap {
                    if (it) Mono.error(Exception())
                    else Mono.just(userDto.username)
                }
                .map {
                    User(
                            id = null,
                            username = userDto.username,
                            password = passwordEncoder.encode(userDto.password),
                            isActive = true,
                            role = "ROLE_USER"
                    )
                }
                .flatMap(userRxRepository::save)
    }
}