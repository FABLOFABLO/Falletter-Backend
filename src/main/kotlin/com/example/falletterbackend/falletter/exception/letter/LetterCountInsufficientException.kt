package com.example.falletterbackend.falletter.exception.letter

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object LetterCountInsufficientException : FalletterException(ErrorCode.LETTER_COUNT_INSUFFICIENT)
