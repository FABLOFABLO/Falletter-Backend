package com.example.falletterbackend.falletter.exception.auth

import org.springframework.http.HttpStatus

class CustomException(
    val httpStatus: HttpStatus,
    override val message: String
) : RuntimeException(message)
