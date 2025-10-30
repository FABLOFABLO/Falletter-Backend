package com.example.falletterbackend.falletter.exception.community

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object CommunityNotFoundException : FalletterException(ErrorCode.COMMUNITY_NOT_FOUND)
