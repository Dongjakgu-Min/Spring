package com.example.memo.controller

import com.example.memo.dto.MemoDto
import com.example.memo.repository.MemoRepository
import com.example.memo.service.MemoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class MemoController @Autowired constructor(
        val memoService: MemoService
) {
    @GetMapping("/all")
    fun findAll() = memoService.findAllMemo()

    @GetMapping("/memo/{id}")
    fun findById(@PathVariable id: Long) = memoService.getOne(id)

    @GetMapping("/username/{username}")
    fun findByUsername(@PathVariable username: String) = memoService.findAllByUsername(username)

    @GetMapping("/tag/{tag}")
    fun findByTag(@PathVariable tag: String) = memoService.findAllByTag(tag)

    @PostMapping("/create")
    fun createMemo(@RequestBody memo: MemoDto) = memoService.createMemo(memo)
}