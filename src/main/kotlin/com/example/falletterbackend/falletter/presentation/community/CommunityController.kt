package com.example.falletterbackend.falletter.presentation.community

import com.example.falletterbackend.falletter.dto.community.request.CommunityPostsRequest
import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsResponse
import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.communtiy.CommunityCreatePostService
import com.example.falletterbackend.falletter.service.communtiy.CommunityGetAllPostService
import com.example.falletterbackend.falletter.service.communtiy.CommunityGetPostService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/community")
class CommunityController(
    private val communityCreatePostService: CommunityCreatePostService,
    private val communityGetAllPostService: CommunityGetAllPostService,
    private val communityGetPostService: CommunityGetPostService
) {
    @PostMapping(RestApiSpec.COMMUNITY_CREATE_POST)
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@RequestBody @Valid request: CommunityPostsRequest) {
        communityCreatePostService.execute(request)
    }

    @GetMapping(RestApiSpec.COMMUNITY_LIST_READ_POST)
    @ResponseStatus(HttpStatus.OK)
    fun getListPost(): List<CommunityPostsResponse> { return communityGetAllPostService.execute() }

    @GetMapping(RestApiSpec.COMMUNITY_READ_POST)
    @ResponseStatus(HttpStatus.OK)
    fun getPost(@PathVariable("post-id") id: Long): CommunityPostsResponse {
        return communityGetPostService.execute(id)
    }
}