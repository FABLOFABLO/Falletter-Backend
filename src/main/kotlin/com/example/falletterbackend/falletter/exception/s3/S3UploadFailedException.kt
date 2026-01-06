package com.example.falletterbackend.falletter.exception.s3

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object S3UploadFailedException : FalletterException(ErrorCode.S3_UPLOAD_FAILED)
