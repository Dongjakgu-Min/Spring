package com.example.security.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

/*
    AuthenticationProvider는 form에서 입력한 로그인 정보와 DB에서 가져온 사용자 정보를 비교하는 인터페이스이다.
 */
@Service
class MyAuthenticationProvider : AuthenticationProvider {
    companion object {
        val idAndPw = mapOf("admin" to "123", "user" to "456")
        val idAndRole = mapOf("admin" to "ROLE_ADMIN", "user" to "ROLE_USER")
    }

    override fun authenticate(authentication: Authentication?): Authentication {
        // authenticate 함수는 authenticate 해주는 Method이다. authentication 객체는 HTML에서 받아오는 ID, PW가 담긴 객체이다.
        val authToken = authentication as UsernamePasswordAuthenticationToken
        // authentication의 객체로 username과 password가 담긴 UsernamepasswordAuthenticationToken을 만들어 준다.

        return when(idAndPw[authToken.principal.toString()] == authToken.credentials.toString()) {
            /*
                UsernamePasswordAuthenticationToken의 principal은 ID 정보를 담고 있고, credentials은 Password를 담고 있다.
                일반적으로 Principal은 인증한 객체, 즉 DB에서 가져온 사용자 정보를 읽어올 수 있다. 그리고 Credentials는 Principal
                과 마찬가지로 구현에 따라 어떠한 정보가 들어갈 지 모르지만 일반적으로 암호화된 정보를 저장한다.
             */
            false -> {
                throw BadCredentialsException("Id & Password are Not Matched")
            }
            true -> {
                val userDetail = MyUserDetail(authToken.principal.toString(), authToken.credentials.toString(),
                        idAndRole[authToken.principal.toString()]!!)
                UsernamePasswordAuthenticationToken(userDetail, userDetail.pw, userDetail.authorities)
            }
        }
    }

    override fun supports(authentication: Class<*>?): Boolean = true
}