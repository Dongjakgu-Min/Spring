package com.example.memo.config

import com.example.memo.config.jwt.JwtConfig
import com.example.memo.config.jwt.JwtTokenProvider
import com.example.memo.service.user.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.CachingUserDetailsService
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class WebSecurityConfig @Autowired constructor(
        @Autowired private val jwtTokenProvider: JwtTokenProvider,
        @Autowired private val userDetailsService: MyUserDetailsService,
        @Autowired private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : WebSecurityConfigurerAdapter() {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity) {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/memo/").permitAll()
                .and()
                .apply(JwtConfig(jwtTokenProvider))
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
    }
}