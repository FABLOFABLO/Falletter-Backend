package com.example.falletterbackend.falletter.exception.auth

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object AccessDeniedException : FalletterException(ErrorCode.ACCESS_DENIED)
