package com.example.falletterbackend.falletter.exception.answer

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object AnswerNotFoundException : FalletterException(ErrorCode.ANSWER_NOT_FOUND)
