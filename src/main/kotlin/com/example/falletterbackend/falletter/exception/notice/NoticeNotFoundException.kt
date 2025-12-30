package com.example.falletterbackend.falletter.exception.notice

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object NoticeNotFoundException : FalletterException(ErrorCode.NOTICE_NOT_FOUND)
