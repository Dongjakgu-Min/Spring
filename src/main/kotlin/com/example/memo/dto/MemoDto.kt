package com.example.memo.dto

import com.example.memo.model.Memo

class MemoDto (
        var title: String,
        var content: String,
        var isPublic: Boolean,
        var tag: String?
)