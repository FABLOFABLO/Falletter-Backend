package com.example.falletterbackend.falletter.exception.history

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object HistoryNotFoundException : FalletterException(ErrorCode.HISTORY_NOT_FOUND)
