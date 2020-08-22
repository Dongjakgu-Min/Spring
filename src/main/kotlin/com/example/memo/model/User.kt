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
        var isActive: Boolean,
        @Column(name = "role")
        var role: String
) {
        fun update(username: String?, password: String?) {
                if (username == null && password != null) this.password = password
                if (password == null && username != null) this.username = username
                if (password != null && username != null) {
                        this.password = password
                        this.username = username
                }
        }

        fun changeRole(isAdmin: Boolean) {
                role = if (isAdmin) "ROLE_ADMIN" else "ROLE_USER"
        }
}