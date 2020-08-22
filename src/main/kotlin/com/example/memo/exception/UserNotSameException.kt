package com.example.memo.exception

import org.springframework.http.HttpStatus

class UserNotSameException : ApiException(HttpStatus.CONFLICT, "RequestUser is not same")