package com.example.falletterbackend.falletter.presentation.community

import com.example.falletterbackend.falletter.dto.community.request.CommunityPostsRequest
import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsListResponse
import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsResponse
import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.communtiy.*
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
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
    private val communityGetPostService: CommunityGetPostService,
    private val communityUpdatePostService: CommunityUpdatePostService,
    private val communityDeletePostService: CommunityDeletePostService
) {
    @PostMapping(RestApiSpec.COMMUNITY_CREATE_POST)
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@RequestBody @Valid request: CommunityPostsRequest) {
        communityCreatePostService.execute(request)
    }

    @GetMapping(RestApiSpec.COMMUNITY_LIST_READ_POST)
    @ResponseStatus(HttpStatus.OK)
    fun getListPost(): List<CommunityPostsListResponse> { return communityGetAllPostService.execute() }

    @GetMapping(RestApiSpec.COMMUNITY_READ_POST)
    @ResponseStatus(HttpStatus.OK)
    fun getPost(@PathVariable("post-id") id: Long): CommunityPostsResponse {
        return communityGetPostService.execute(id)
    }

    @PatchMapping(RestApiSpec.COMMUNITY_UPDATE_POST)
    @ResponseStatus(HttpStatus.OK)
    fun updatePost(
        @PathVariable("post-id") id: Long,
        @RequestBody @Valid request: CommunityPostsRequest
    ) {
        communityUpdatePostService.execute(id, request)
    }

    @DeleteMapping(RestApiSpec.COMMUNITY_DELETE_POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(@PathVariable("post-id") id: Long) {
        communityDeletePostService.execute(id)
    }
}