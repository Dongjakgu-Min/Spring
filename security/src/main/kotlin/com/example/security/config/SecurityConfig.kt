package com.example.security.config

import com.example.security.security.MyAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/*
    WebSecurityConfigurerAdapter는 WebSecurityConfigurer Instance를 만드는 데 편의를 주기 위한 추상 class이다.
    그리고 WebSecurityConfig는 사용자 인증에 대한 정보를 포함하고 있다.
*/
@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    fun provider() = MyAuthenticationProvider()

    // AuthenticationManagerBuilder를 통해서 인증 객체를 만들 수 있다.
    override fun configure(auth: AuthenticationManagerBuilder) {
        //AuthenticationManageBuilder를 provider를 통해 실질적 인증절차를 진행하게 해 준다.
        auth.authenticationProvider(provider())
    }

    // 해당 접근을 막는다. 전역 보안에 영향을 주는 구성을 설정한다.
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/resource/**", "/static/**", "/favicon.ico")
    }

    // 어떠한 URL 경로를 보호해야 하는지에 대해 정의하거나 자신만의 인증 매커니즘을 정의한다.
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().httpBasic()
        http.headers().frameOptions().disable()

        http.authorizeRequests()
                // '/login'에 대해서는 모두 접근 가능하게 설정한다.
                .antMatchers("/login").permitAll()
//                .anyRequest().permitAll()
                // 그 이외에는 인증이 되어야 한다.
                .anyRequest().authenticated()
//                .antMatchers("/index").authenticated()
                .and()
                .formLogin()
                // 'auth'는 로그인을 처리할 페이지, 'login'은 로그인 페이지
                .loginPage("/login").loginProcessingUrl("/auth")
                // 로그인에 성공했을 때 접속할 url
                .defaultSuccessUrl("/index", true)
                // 로그인에 실패했을 때 접속할 url
                .failureUrl("/login?error")
                .and()
                // 로그아웃
                .logout()
                // 로그아웃 성공 시 이동할 url
                .logoutSuccessUrl("/login?logout")
                // 로그인에 관련된 쿠키 및 세션 삭제
                .deleteCookies("JSESSIONID")
                // 자신이 로그인 했던 모든 정보를 삭제
                .invalidateHttpSession(true)

        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true)
    }
}