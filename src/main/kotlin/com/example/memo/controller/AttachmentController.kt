package com.example.memo.controller

import com.example.memo.dto.AttachmentDto
import com.example.memo.service.AttachmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import reactor.core.publisher.Mono
import javax.print.attribute.standard.Media

@RestController
@RequestMapping("/attach/")
class AttachmentController @Autowired constructor(
        private val attachmentService: AttachmentService
) {
    @PostMapping("/memo/{memoId}", consumes = [MediaType.ALL_VALUE])
    fun save(@RequestPart file: Mono<FilePart>,
             @PathVariable memoId: Long) = attachmentService.save(file, memoId)
}