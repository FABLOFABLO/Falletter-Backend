package com.example.falletterbackend.falletter.exception.question

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

class QuestionNotFoundException : FalletterException(ErrorCode.QUESTION_NOT_FOUND)
