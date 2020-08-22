package com.example.memo.service

import com.example.memo.model.Attachment
import com.example.memo.model.Memo
import com.example.memo.repository.memo.MemoRepository
import com.example.memo.repository.memo.MemoRxRepository
import com.example.memo.repository.upload.AttachmentRxRepository
import com.example.memo.repository.user.UserRxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.codec.multipart.FilePart
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.File

@Service
class AttachmentService @Autowired constructor(
        private val attachmentRxRepository: AttachmentRxRepository,
        private val userRxRepository: UserRxRepository,
        private val memoRxRepository: MemoRxRepository,
        private val memoRepository: MemoRepository
) {
    fun findAttachmentsByMemoId(memoId: Long): Flux<Attachment> {
        return attachmentRxRepository.findAllByMemoIdAndIsDeleted(memoId, false)
    }

    fun save(filePart: Mono<FilePart>, memoId: Long): Mono<Unit> {
        val userDetails = SecurityContextHolder.getContext().authentication as UserDetails

        return filePart.flatMap {
            insertAttachment(memoId, userDetails.username, it)
        }
    }

    private fun insertAttachment(
            memoId: Long,
            username: String,
            filePart: FilePart
    ): Mono<Unit> {
        val filename = System.currentTimeMillis().toString()

        return Mono.fromCallable { File("/upload/$filename") }
                .flatMap {
                    filePart.transferTo(it)
                            .then(Mono.just(it))
                }
                .flatMap { File ->
                    userRxRepository.findByUsernameAndIsActiveIfExist(username, true)
                            .map {
                                Attachment(
                                        null,
                                        filename,
                                        filePart.filename(),
                                        it,
                                        memoId,
                                        File.path,
                                        false
                                )
                            }.flatMap {
                                attachmentRxRepository.save(it)
                            }
                }
    }
}