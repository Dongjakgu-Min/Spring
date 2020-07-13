package com.example.memo.repository.user

import com.example.memo.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Repository
class UserRxRepositoryImpl @Autowired constructor(
        private val userRepository: UserRepository
) : UserRxRepository {
    override fun existByUsername(username: String): Mono<Boolean> {
        return Mono.fromCallable { userRepository.existsByUsername(username) }
                .subscribeOn(Schedulers.elastic())
    }

    override fun findByUsername(username: String): Mono<User> {
        return Mono.fromCallable { userRepository.findByUsername(username) }
                .subscribeOn(Schedulers.elastic())
    }

}