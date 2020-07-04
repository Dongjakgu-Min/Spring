package com.example.memo.model

import java.util.*
import javax.persistence.*

@Entity
class Memo(
        @Id @GeneratedValue
        val id: Long? = null,
        @Column(name = "username", nullable = false)
        var username: String,
        @Column(name = "title", nullable = false)
        var title: String,
        @Column(name = "content", nullable = false)
        var content: String,
        @Column(name = "is_public", nullable = false)
        var isPublic: Boolean,
        @Column(name = "tag")
        var tag: String?,
        @Column(name = "created_at")
        var createdAt: Date? = Date(System.currentTimeMillis())
)