package com.example.memo.repository.upload

import com.example.memo.model.Attachment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AttachmentRepository : JpaRepository<Attachment, Long> {
    fun save(attachment: Attachment)

    fun findAllByFilenameAndIsDeleted(filename: String, isDeleted: Boolean): List<Attachment>
    fun findAllByMemoIdAndIsDeleted(memoId: Long, isDeleted: Boolean): List<Attachment>

}