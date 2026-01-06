package com.example.falletterbackend.falletter.exception.auth

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object TokenInvalidException : FalletterException(ErrorCode.TOKEN_INVALID)
