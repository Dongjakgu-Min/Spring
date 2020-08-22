package com.example.memo.model

import java.text.DateFormat
import java.util.*
import javax.persistence.*

@Entity
class Attachment (
        @Id @GeneratedValue
        val id: Long ?= null,
        @Column(nullable = false)
        var filename: String,
        @Column(nullable = false)
        var realname: String,
        @ManyToOne
        var user: User,
        @Column(nullable = false)
        var memoId: Long,
        @Column(nullable = false)
        var path: String,
        @Column(nullable = false)
        var isDeleted: Boolean,
        @Column
        var createdAt: Date = Date(System.currentTimeMillis())
)