package com.example.memo.exception

import org.springframework.http.HttpStatus


class MemoNotFoundException : ApiException(HttpStatus.CONFLICT, "unable to find memo")