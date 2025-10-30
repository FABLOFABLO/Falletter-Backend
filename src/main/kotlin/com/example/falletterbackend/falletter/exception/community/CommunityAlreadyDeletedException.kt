package com.example.falletterbackend.falletter.exception.community

import com.example.falletterbackend.common.error.exception.ErrorCode
import com.example.falletterbackend.common.error.exception.FalletterException

object CommunityAlreadyDeletedException : FalletterException(ErrorCode.COMMUNITY_ALREADY_DELETED)
