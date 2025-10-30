package com.example.falletterbackend.falletter.exception.item

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object BrickCountInsufficientException : FalletterException(ErrorCode.BRICK_COUNT_INSUFFICIENT)
