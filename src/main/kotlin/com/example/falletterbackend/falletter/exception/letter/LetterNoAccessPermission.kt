package com.example.falletterbackend.falletter.exception.letter

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object LetterNoAccessPermission : FalletterException(ErrorCode.LETTER_NO_ACCESS_PERMISSION)