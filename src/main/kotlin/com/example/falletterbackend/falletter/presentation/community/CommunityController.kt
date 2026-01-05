package com.example.falletterbackend.falletter.presentation.community

import com.example.falletterbackend.falletter.dto.community.request.CommunityPostsRequest
import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsListResponse
import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.community.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
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

@Tag(name = "community", description = "커뮤니티 게시판 API")
@RestController
@RequestMapping("/community")
class CommunityController(
    private val communityCreatePostService: CommunityCreatePostService,
    private val communityGetAllPostService: CommunityGetAllPostService,
    private val communityGetPostService: CommunityGetPostService,
    private val communityUpdatePostService: CommunityUpdatePostService,
    private val communityDeletePostService: CommunityDeletePostService
) {
    @Operation(summary = "게시글 작성", description = "새로운 게시글을 작성합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "게시글 작성 성공")
    )
    @PostMapping(RestApiSpec.COMMUNITY_CREATE_POST)
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@RequestBody @Valid request: CommunityPostsRequest) { communityCreatePostService.execute(request) }

    @Operation(summary = "게시글 목록 조회", description = "전체 게시글 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.COMMUNITY_LIST_READ_POST)
    @ResponseStatus(HttpStatus.OK)
    fun getListPost(): List<CommunityPostsListResponse> { return communityGetAllPostService.execute() }

    @Operation(summary = "게시글 상세 조회", description = "게시글 상세 내용을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    )
    @GetMapping(RestApiSpec.COMMUNITY_READ_POST)
    @ResponseStatus(HttpStatus.OK)
    fun getPost(
        @Parameter(description = "게시글 ID", example = "1")
        @PathVariable("post-id") id: Long
    ): CommunityPostsResponse { return communityGetPostService.execute(id) }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "수정 성공"),
        ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    )
    @PatchMapping(RestApiSpec.COMMUNITY_UPDATE_POST)
    @ResponseStatus(HttpStatus.OK)
    fun updatePost(
        @Parameter(description = "게시글 ID", example = "1")
        @PathVariable("post-id") id: Long,
        @RequestBody @Valid request: CommunityPostsRequest
    ) {
        communityUpdatePostService.execute(id, request)
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "삭제 성공"),
        ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    )
    @DeleteMapping(RestApiSpec.COMMUNITY_DELETE_POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(
        @Parameter(description = "게시글 ID", example = "1")
        @PathVariable("post-id") id: Long
    ) { communityDeletePostService.execute(id) }
}
