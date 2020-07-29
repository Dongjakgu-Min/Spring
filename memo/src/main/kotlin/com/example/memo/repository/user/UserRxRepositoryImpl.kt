package com.example.memo.repository.user

import com.example.memo.dto.UserDto
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
    override fun save(user: User): Mono<Unit> {
        return Mono.fromCallable { userRepository.save(user) }
                .subscribeOn(Schedulers.elastic())
    }

    override fun existByUsername(username: String): Mono<Boolean> {
        return Mono.fromCallable { userRepository.existsByUsername(username) }
                .subscribeOn(Schedulers.elastic())
    }

    override fun existsByUsernameAndIsActive(username: String, isActive: Boolean): Mono<Boolean> {
        return Mono.fromCallable { userRepository.existsByUsernameAndIsActive(username, isActive) }
                .subscribeOn(Schedulers.elastic())
    }

    override fun findByUsername(username: String): Mono<User> {
        return Mono.fromCallable { userRepository.findByUsername(username) }
                .subscribeOn(Schedulers.elastic())
    }

    override fun findByUsernameAndIsActive(username: String, isActive: Boolean): Mono<User> {
        return Mono.fromCallable { userRepository.findByUsernameAndIsActive(username, isActive) }
                .subscribeOn(Schedulers.elastic())
    }

    override fun findByUsernameAndIsActiveIfExist(username: String, isActive: Boolean): Mono<User> {
        return existsByUsernameAndIsActive(username, isActive)
                .flatMap {
                    if (!it) Mono.error(Exception())
                    else Mono.just(it)
                }
                .flatMap {
                    findByUsernameAndIsActive(username, isActive)
                }
    }

}