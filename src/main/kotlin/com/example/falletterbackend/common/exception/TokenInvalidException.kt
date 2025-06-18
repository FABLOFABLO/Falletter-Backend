package com.example.falletterbackend.common.exception

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object TokenInvalidException : FalletterException(ErrorCode.TOKEN_INVALID)