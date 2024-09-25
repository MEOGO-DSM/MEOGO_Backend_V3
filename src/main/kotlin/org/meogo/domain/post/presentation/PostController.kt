package org.meogo.domain.post.presentation

import lombok.RequiredArgsConstructor
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

@RequiredArgsConstructor
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @Valid
        @RequestPart("request")
        request: PostRequest,
        @RequestPart("image") image: MultipartFile?
    ) =
        createPostService.execute(request, image)

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@RequestParam("post_id") id: Long) =
        deletePostService.execute(id)

    @PatchMapping("/modify")
    fun modify(
        @RequestParam("post_id") id: Long,
        @Valid
        @RequestPart("request")
        request: PostRequest,
        @RequestPart("image") image: MultipartFile?
    ) = modifyPostService.execute(id, request, image)

    @GetMapping("/query/all")
    fun queryAll() = queryAllPostService.execute()

    @GetMapping("/query")
    fun querySchool(@RequestParam(name = "school_id") schoolId: Int) = querySchoolPostService.execute(schoolId)

    @GetMapping("/query/detail")
    fun queryPostDetail(@RequestParam(name = "post_id") id: Long) =
        queryPostDetailService.execute(id)

    @GetMapping("/query/my")
    fun queryMyPosts() = queryMyPostService.execute()
}
