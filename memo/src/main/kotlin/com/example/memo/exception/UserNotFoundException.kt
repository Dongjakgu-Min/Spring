package com.example.memo.exception

import org.springframework.http.HttpStatus


class UserNotFoundException : ApiException(HttpStatus.CONFLICT, "User Not Exists")