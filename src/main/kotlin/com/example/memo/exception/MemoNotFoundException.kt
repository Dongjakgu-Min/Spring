package com.example.memo.exception

import org.springframework.http.HttpStatus


class MemoNotFoundException : ApiException(HttpStatus.NOT_FOUND, "unable to find memo")