package com.example.falletterbackend.falletter.exception.admin

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object AlreadyAdminEmailException : FalletterException(ErrorCode.ALREADY_ADMIN_EMAIL)
