package com.example.memo.database

import javax.persistence.*

@Entity
class User(
        @GeneratedValue @Id
        var id : Long ?= null,
        @Column(nullable = false, unique = true)
        var username : String,
        @Column(nullable = false)
        var password : String,
        @Column(nullable = false)
        var isAdmin : Boolean = false,
        @Column(nullable = false)
        var role : String = "ROLE_USER"
)

@Entity
class Memo(
        @GeneratedValue @Id
        var id : Long ?= null,
        @Column(nullable = false)
        var title : String,
        @Column
        var content : String,
        @Column
        var tag : String,
        @Column
        var isOpen : Boolean = false,
        @Column(nullable = false)
        var username: String,
        @ManyToOne
        var author : User
)