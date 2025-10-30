package com.example.falletterbackend.common.exception

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object TokenExpiredException : FalletterException(ErrorCode.TOKEN_EXPIRED)
