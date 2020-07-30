package com.example.memo.controller

import com.example.memo.dto.MemoDto
import com.example.memo.service.MemoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class MemoController @Autowired constructor(
        val memoService: MemoService
) {
    @GetMapping("/all")
    fun findAll() = memoService.findAllMemo()

    @GetMapping("/memo/{id}")
    fun findById(@PathVariable id: Long) = memoService.getOneByIdIsNotDeleted(id)

    @GetMapping("/tag/{tag}")
    fun findByTag(@PathVariable tag: String) = memoService.findAllByTag(tag)

    @PostMapping("/memo/create")
    fun createMemo(@RequestBody memo: MemoDto) = memoService.createMemo(memo)

    @GetMapping("/memo/username/{username}")
    fun findByUsername(@PathVariable username: String) = memoService.findAllByUsername(username)

    @PostMapping("/memo/{memoId}/edit")
    fun editMemo(@PathVariable memoId: Long, @RequestBody memoDto: MemoDto) = memoService.updateMemo(memoId, memoDto)
}