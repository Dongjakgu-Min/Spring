package com.example.account.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Account(
        @Id @GeneratedValue
        var id: Long? = null,
        @Column
        var username: String,
        @Column
        var phoneNumber: String,
        @Column
        var createdAt: Date = Date(System.currentTimeMillis())
)