package com.example.security.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/*
    UserDetails는 사용자의 정보를 담는 인터페이스이다.
*/
class MyUserDetail(val id: String, val pw: String, val role: String) : UserDetails {
    //계정이 가지고 있는 권한 목록을 Return
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority(role))
        return authorities
    }

    // 계정의 Password Return
    override fun getPassword() = pw
    // 계정의 이름을 Return
    override fun getUsername() = id
    // 계정의 Password가 만료되지 않았는지를 Return, True Return시 만료되지 않음을 의미함.
    override fun isCredentialsNonExpired() = true
    // 계정이 사용 가능한 계정인지를 Return, True Return 시 사용 가능한 계정 인지를 의미함.
    override fun isEnabled() = true
    // 계정이 만료 되지 않았는지를 Return, True Return 시 만료 되지 않음을 의미함.
    override fun isAccountNonExpired() = true
    // 계정이 잠겨 있지 않았는지를 Return, True Return 시 계정이 잠겨 있지 않음을 의미함.
    override fun isAccountNonLocked() = true
}