package com.example.falletterbackend.falletter.presentation.community

import com.example.falletterbackend.falletter.dto.community.request.CommunityCreatePostRequest
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.communtiy.CommunityCreatePostService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/community")
class CommunityController(
    private val communityCreatePostService: CommunityCreatePostService
) {
    @PostMapping(RestApiSpec.COMMUNITY_CREATE_POST)
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@RequestBody @Valid request: CommunityCreatePostRequest){
        communityCreatePostService.execute(request)
    }
}