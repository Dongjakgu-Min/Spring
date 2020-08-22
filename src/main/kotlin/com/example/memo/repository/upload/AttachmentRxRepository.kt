package com.example.memo.repository.upload

import com.example.memo.model.Attachment
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface AttachmentRxRepository {
    fun save(attachment: Attachment): Mono<Unit>

    fun findAllByFilenameAndIsDeleted(filename: String, isDeleted: Boolean): Flux<Attachment>
    fun findAllByMemoIdAndIsDeleted(memoId: Long, isDeleted: Boolean): Flux<Attachment>
}