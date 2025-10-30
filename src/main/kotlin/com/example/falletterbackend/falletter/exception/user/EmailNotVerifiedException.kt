package com.example.falletterbackend.falletter.exception.user

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object EmailNotVerifiedException : FalletterException(ErrorCode.EMAIL_NOT_VERIFIED)