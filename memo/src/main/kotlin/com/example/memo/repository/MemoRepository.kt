package com.example.memo.repository

import com.example.memo.model.Memo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemoRepository : JpaRepository<Memo, Long>