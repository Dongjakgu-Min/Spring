package com.example.memo.service

import com.example.memo.dto.UserDto
import com.example.memo.model.User
import com.example.memo.repository.user.UserRepository
import com.example.memo.repository.user.UserRxRepository
import com.example.memo.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthService @Autowired constructor(
        private val userRxRepository: UserRxRepository,
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtTokenProvider: JwtTokenProvider
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

    fun login(userDto: UserDto): Mono<String> {
//        val user: User = userRepository.findByUsername(userDto.username)
//        if (!passwordEncoder.matches(userDto.password, user.password)) {
//            throw Exception()
//        }
//
//        return jwtTokenProvider.createToken(user.username, listOf(user.role))
        return userRxRepository.findByUsername(userDto.username)
                .flatMap {
                    if (!passwordEncoder.matches(userDto.password, it.password))
                        throw Exception()
                    else Mono.just(it)
                }
                .map {
                    jwtTokenProvider.createToken(it.username, listOf(it.role))
                }
    }
}