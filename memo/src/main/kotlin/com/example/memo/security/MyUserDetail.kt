package com.example.memo.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MyUserDetail(val id : String, val pw : String, val role : String) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority(role))
        return authorities
    }
    override fun getPassword() = pw

    override fun getUsername() = id
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
}