package com.example.memo.model

import java.util.*
import javax.persistence.*

@Entity
data class Memo(
        @Id @GeneratedValue
        val id: Long? = null,
        @Column(nullable = false)
        var title: String,
        @Column
        var content: String,
        @Column
        var tag: String,
        @Column(nullable = false)
        var isOpen: Boolean = true,
        @Column
        var createdAt: Date = Date(System.currentTimeMillis()),
        @ManyToOne
        val author: User
)