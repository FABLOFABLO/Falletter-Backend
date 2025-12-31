package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.response.UserInfoResponse
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service

@Service
class UserInfoService(
    private val userFacade: UserFacade
) {
    fun execute(): UserInfoResponse {
        val user = userFacade.getCurrentUser()

        return UserInfoResponse(
            email = user.email,
            schoolNumber = user.schoolNumber,
            name = user.name,
            gender = user.gender,
            theme = user.theme,
            profileImage = user.profileImage,
            id = user.id
        )
    }
}
