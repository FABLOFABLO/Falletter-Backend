package com.example.falletterbackend.falletter.exception.comment

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object CommentNotFoundException : FalletterException(ErrorCode.COMMENT_NOT_FOUND)
