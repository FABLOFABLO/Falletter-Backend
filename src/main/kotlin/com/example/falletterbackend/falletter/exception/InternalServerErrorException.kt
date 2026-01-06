package com.example.falletterbackend.falletter.exception

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object InternalServerErrorException : FalletterException(ErrorCode.INTERNAL_SERVER_ERROR)
