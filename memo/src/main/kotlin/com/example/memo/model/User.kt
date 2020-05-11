package com.example.memo.model

import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
data class User(
        @Id @GeneratedValue
        val id: Long ?= null,
        @Column(unique = true, nullable = false)
        var username: String,
        @Column(nullable = false)
        var password: String,
        @Column
        var role: String
)
