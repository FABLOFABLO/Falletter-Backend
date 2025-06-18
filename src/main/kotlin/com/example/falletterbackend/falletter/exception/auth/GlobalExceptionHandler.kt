package com.example.falletterbackend.falletter.exception.auth

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UnExistVerifyCodeException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUnExistVerifyCodeException(e: UnExistVerifyCodeException): String {
        return "인증 코드가 존재하지 않습니다."
    }

    @ExceptionHandler(UnMatchVerifyCodeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleUnMatchVerifyCodeException(e: UnMatchVerifyCodeException): String {
        return "인증 코드가 일치하지 않습니다."
    }
}