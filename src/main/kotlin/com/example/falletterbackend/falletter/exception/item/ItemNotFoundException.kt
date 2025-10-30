package com.example.falletterbackend.falletter.exception.item

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object ItemNotFoundException : FalletterException(ErrorCode.ITEM_NOT_FOUND)
