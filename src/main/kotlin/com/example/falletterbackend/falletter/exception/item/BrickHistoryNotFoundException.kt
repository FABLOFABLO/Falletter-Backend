package com.example.falletterbackend.falletter.exception.item

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object BrickHistoryNotFoundException : FalletterException(ErrorCode.BRICK_HISTORY_NOT_FOUND)
