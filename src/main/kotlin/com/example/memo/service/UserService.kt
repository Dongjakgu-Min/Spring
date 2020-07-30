package com.example.memo.service

import com.example.memo.dto.RoleDto
import com.example.memo.repository.user.UserRxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService @Autowired constructor(
        private val userRxRepository: UserRxRepository
) {
    fun changeRole(username: String): Mono<Unit> {
        return userRxRepository.findByUsernameAndIsActiveIfExist(username, true)
                .map {
                    if (it.role == "ROLE_USER") it.role = "ROLE_ADMIN"
                    else it.role = "ROLE_USER"
                    it
                }
                .flatMap { userRxRepository.save(it) }
    }
}