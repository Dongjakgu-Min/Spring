package com.example.memo.config

import com.example.memo.database.UserRepository
import com.example.memo.security.MyAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val userRepository : UserRepository) : WebSecurityConfigurerAdapter() {
    @Autowired
    fun provider() = MyAuthenticationProvider(userRepository)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(provider())
    }
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/resource/**", "/static/**", "/favicon.ico")
    }
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().httpBasic()
        http.headers().frameOptions().disable()
        http.authorizeRequests()
                .antMatchers("/login", "/api/user/**").permitAll()
                .anyRequest().authenticated()

        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true)
    }
}