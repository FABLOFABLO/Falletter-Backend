package com.example.falletterbackend.falletter.presentation.admin.community

import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.communtiy.AdminCommunityDeleteService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminCommunityController(
    private val adminCommunityDeleteService: AdminCommunityDeleteService
) {
    @PatchMapping(RestApiSpec.ADMIN_COMMUNITY_STATUS)
    @ResponseStatus(HttpStatus.OK)
    fun updateCommunityStatus(@PathVariable("community-id") id: Long) {
        adminCommunityDeleteService.execute(id)
    }
}
