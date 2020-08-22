package com.example.memo.service

import com.example.memo.repository.user.UserRxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AdminService @Autowired constructor(
        private val userRxRepository: UserRxRepository
) {
    fun forceSignOut(username: String): Mono<Unit> {
        return userRxRepository.findByUsername(username)
                .flatMap {
                    it.isActive = false
                    userRxRepository.save(it)
                }
    }

    fun toAdmin(username: String): Mono<Unit> {
        return userRxRepository.findByUsernameAndIsActiveIfExist(username, true)
                .flatMap {
                    it.role = "ROLE_ADMIN"
                    userRxRepository.save(it)
                }
    }

    fun toUser(username: String): Mono<Unit> {
        return userRxRepository.findByUsernameAndIsActiveIfExist(username, true)
                .flatMap {
                    it.role = "ROLE_USER"
                    userRxRepository.save(it)
                }
    }
}