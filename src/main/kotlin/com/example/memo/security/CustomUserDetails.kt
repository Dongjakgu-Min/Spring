package com.example.memo.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(val USERNAME: String, val PASSWORD: String, val AUTHORITY: String) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val auth: ArrayList<GrantedAuthority> = ArrayList<GrantedAuthority>()
        auth.add(SimpleGrantedAuthority(AUTHORITY))
        return auth
    }

    override fun isEnabled(): Boolean = true
    override fun getUsername(): String = USERNAME
    override fun isCredentialsNonExpired(): Boolean = true
    override fun getPassword(): String = PASSWORD
    override fun isAccountNonExpired(): Boolean =true
    override fun isAccountNonLocked(): Boolean = true

}