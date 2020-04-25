package com.example.memo.database

class FormMemo(var title: String, var content: String, var tag: String, var isOpen: Boolean, var username: String) {
    fun output(): Memo {
        return Memo(null, title, content, tag, isOpen)
    }
}