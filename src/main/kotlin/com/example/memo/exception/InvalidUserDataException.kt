package com.example.memo.exception

import org.springframework.http.HttpStatus

class InvalidUserDataException : ApiException(HttpStatus.NOT_FOUND, "Invalid User Data")