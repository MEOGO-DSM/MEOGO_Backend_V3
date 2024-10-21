package org.meogo.domain.post.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.meogo.domain.post.presentation.dto.request.PostRequest
import org.meogo.domain.post.service.CreatePostService
import org.meogo.domain.post.service.DeletePostService
import org.meogo.domain.post.service.ModifyPostService
import org.meogo.domain.post.service.QueryAllPostService
import org.meogo.domain.post.service.QueryMyPostService
import org.meogo.domain.post.service.QueryPostDetailService
import org.meogo.domain.post.service.QuerySchoolPostService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@Tag(name = "Community API")
@RestController
@RequestMapping("/community")
class PostController(
    private val createPostService: CreatePostService,
    private val queryAllPostService: QueryAllPostService,
    private val querySchoolPostService: QuerySchoolPostService,
    private val deletePostService: DeletePostService,
    private val modifyPostService: ModifyPostService,
    private val queryPostDetailService: QueryPostDetailService,
    private val queryMyPostService: QueryMyPostService
) {

    @Operation(summary = "게시글 작성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @Valid
        @RequestPart("request")
        request: PostRequest,
        @RequestPart("image") image: MultipartFile?
    ) =
        createPostService.execute(request, image)

    @Operation(summary = "댓글 삭제")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@RequestParam("post_id") id: Long) =
        deletePostService.execute(id)

    @Operation(summary = "댓글 수정")
    @PatchMapping("/modify")
    fun modify(
        @RequestParam("post_id") id: Long,
        @Valid
        @RequestPart("request")
        request: PostRequest,
        @RequestPart("image") image: MultipartFile?
    ) = modifyPostService.execute(id, request, image)

    @Operation(summary = "모든 게시글 조회")
    @GetMapping("/query/all")
    fun queryAll() = queryAllPostService.execute()

    @Operation(summary = "학교별 게시글 조회")
    @GetMapping("/query")
    fun querySchool(@RequestParam(name = "school_id") schoolId: Int) = querySchoolPostService.execute(schoolId)

    @Operation(summary = "게시글 상세보기")
    @GetMapping("/query/detail")
    fun queryPostDetail(@RequestParam(name = "post_id") id: Long) =
        queryPostDetailService.execute(id)

    @Operation(summary = "내가 쓴 게시글 조회")
    @GetMapping("/query/my")
    fun queryMyPosts() = queryMyPostService.execute()
}
