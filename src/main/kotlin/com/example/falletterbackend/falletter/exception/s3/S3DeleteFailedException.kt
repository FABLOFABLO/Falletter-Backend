package com.example.falletterbackend.falletter.exception.s3

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object S3DeleteFailedException : FalletterException(ErrorCode.S3_DELETE_FAILED)
