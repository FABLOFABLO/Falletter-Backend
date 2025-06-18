package com.example.falletterbackend.falletter.exception.community

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object PostNotFoundException : FalletterException(ErrorCode.POST_NOT_FOUND)