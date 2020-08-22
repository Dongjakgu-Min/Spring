package com.example.memo.repository.memo

import com.example.memo.model.Memo
import com.example.memo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemoRepository : JpaRepository<Memo, Long> {
    fun save(memo: Memo)
    fun existsByIdAndIsDeleted(memoId: Long, isDeleted: Boolean): Boolean

    fun findAllByTag(tag: String?): List<Memo>
    fun findAllByUserAndIsDeleted(user: User, isDeleted: Boolean): List<Memo>

    fun findOneById(id: Long): Memo
}