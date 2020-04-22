package com.example.security.entities

class FormUser(var username: String, var password: String) {
    fun getUser() : User {
        return User(null, username, password)
    }
}