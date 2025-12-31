package com.example.falletterbackend.common.error

import com.example.falletterbackend.common.error.exception.FalletterException

class ErrorResponse(
    val status: Int,
    val message: String,
) {
    companion object {
        fun of(e: FalletterException): ErrorResponse {
            return ErrorResponse(e.status, e.message)
        }

        fun of(status: Int, message: String): ErrorResponse {
            return ErrorResponse(status, message)
        }
    }
}
