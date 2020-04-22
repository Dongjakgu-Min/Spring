package com.example.security.entities

import javax.persistence.*

@Entity
class User(
        @Id @GeneratedValue
        var id: Long ?= null,
        @Column(nullable = false)
        var username: String,
        @Column(nullable = false)
        var password: String
)

@Entity
class Memo(
        @Id @GeneratedValue
        var id: Long ?= null,
        @Column(nullable = false)
        var title: String,
        @Column
        var content: String,
        @Column
        var tag: String,
        @Column(nullable = false)
        var open: Boolean = true,
        @ManyToOne
        var author: User
)