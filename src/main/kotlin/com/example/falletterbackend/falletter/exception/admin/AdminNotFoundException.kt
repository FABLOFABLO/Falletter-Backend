package com.example.falletterbackend.falletter.exception.admin

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object AdminNotFoundException : FalletterException(ErrorCode.ADMIN_NOT_FOUND)
