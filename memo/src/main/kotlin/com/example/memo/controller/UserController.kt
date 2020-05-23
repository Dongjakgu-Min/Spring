package com.example.memo.controller

import com.example.memo.config.jwt.JwtTokenProvider
import com.example.memo.controller.request.UserLoginRequest
import com.example.memo.dto.UserDto
import com.example.memo.model.User
import com.example.memo.repository.UserRepository
import com.example.memo.service.user.MyUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors.toList
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController @Autowired constructor(
        val userService: MyUserService,
        val authenticationManager: AuthenticationManager,
        val jwtTokenProvider: JwtTokenProvider,
        val userRepository: UserRepository
) {

    @PostMapping("/register")
    fun signUp(@RequestBody @Valid body: UserDto) : UserDto {
        return userService.register(body)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid request: UserLoginRequest): MutableMap<String, Any> {
        try {
            val username: String = request.username
            val password: String = request.password
            val authenticator = UsernamePasswordAuthenticationToken(username, password)
            val user: User? = userRepository.findByUsername(request.username)

            authenticationManager.authenticate(authenticator)
            val token: String = jwtTokenProvider.createToken(username, listOf(user!!.role))

            val model: MutableMap<String, Any> = HashMap()
            model["username"] = request.username
            model["token"] = token

            return model
        } catch (e: AuthenticationException) {
            throw BadCredentialsException("Invalid username/password supplied")
        }
    }

    @GetMapping("/me")
    fun currentUser(@AuthenticationPrincipal userDetails: UserDetails): MutableMap<String, Any> {
        val model: MutableMap<String, Any> = HashMap()
        model["username"] = userDetails.username
        model["roles"] = userDetails.authorities.stream().map { it.authority }.collect(toList())

        return model
    }

    @GetMapping("/admin/")
    fun getAdmin() = userRepository.findAll()

    @GetMapping("/user/")
    fun getUser() = userRepository.findAll()
}