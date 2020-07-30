package com.example.memo.controller

import com.example.memo.dto.MemoDto
import com.example.memo.service.MemoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/memo/")
class MemoController @Autowired constructor(
        val memoService: MemoService
) {
    @GetMapping("/all")
    fun findAll() = memoService.findAllMemo()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = memoService.getOneByIdIsNotDeleted(id)

    @GetMapping("/tag/{tag}")
    fun findByTag(@PathVariable tag: String) = memoService.findAllByTag(tag)

    @PostMapping("/create")
    fun createMemo(@RequestBody memo: MemoDto) = memoService.createMemo(memo)

    @GetMapping("/username/{username}")
    fun findByUsername(@PathVariable username: String) = memoService.findAllByUsername(username)

    @PostMapping("/{memoId}/edit")
    fun editMemo(@PathVariable memoId: Long, @RequestBody memoDto: MemoDto) = memoService.updateMemo(memoId, memoDto)

    @GetMapping("/{memoId}/remove")
    fun removeMemo(@PathVariable memoId: Long) = memoService.removeMemo(memoId)
}