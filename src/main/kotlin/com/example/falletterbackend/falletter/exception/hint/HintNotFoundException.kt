package com.example.falletterbackend.falletter.exception.hint

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object HintNotFoundException : FalletterException(ErrorCode.HINT_NOT_FOUND)