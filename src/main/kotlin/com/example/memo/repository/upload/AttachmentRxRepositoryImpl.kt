package com.example.memo.repository.upload

import com.example.memo.model.Attachment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Repository
class AttachmentRxRepositoryImpl @Autowired constructor(
        private val attachmentRepository: AttachmentRepository
) : AttachmentRxRepository {
    override fun save(attachment: Attachment): Mono<Unit> {
        return Mono.fromCallable { attachmentRepository.save(attachment) }
                .subscribeOn(Schedulers.elastic())
    }

    override fun findAllByFilenameAndIsDeleted(filename: String, isDeleted: Boolean): Flux<Attachment> {
        return Mono.fromCallable { attachmentRepository.findAllByFilenameAndIsDeleted(filename, isDeleted) }
                .subscribeOn(Schedulers.elastic())
                .flatMapMany { Flux.fromIterable(it) }
    }

    override fun findAllByMemoIdAndIsDeleted(memoId: Long, isDeleted: Boolean): Flux<Attachment> {
        return Mono.fromCallable { attachmentRepository.findAllByMemoIdAndIsDeleted(memoId, isDeleted) }
                .subscribeOn(Schedulers.elastic())
                .flatMapMany { Flux.fromIterable(it) }
    }

}