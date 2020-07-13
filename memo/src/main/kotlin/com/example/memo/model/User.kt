package com.example.memo.model

import javax.persistence.*

@Entity
class User(
        @Id @GeneratedValue
        val id: Long?,
        @Column(unique = true, name = "username")
        var username: String,
        @Column(name = "password")
        var password: String,
        @Column(name = "is_active")
        var isActive: Boolean
)