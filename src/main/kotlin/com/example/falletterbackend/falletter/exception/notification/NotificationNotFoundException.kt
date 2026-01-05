package com.example.falletterbackend.falletter.exception.notification

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object NotificationNotFoundException : FalletterException(ErrorCode.NOTIFICATION_NOT_FOUND)
