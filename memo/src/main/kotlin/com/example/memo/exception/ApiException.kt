package com.example.memo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class ApiException(status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR, message: String = "Internal Server Error")
    : ResponseStatusException(status, message)