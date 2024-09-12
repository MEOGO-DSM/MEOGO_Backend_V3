package org.meogo.domain.post.present

import lombok.RequiredArgsConstructor
import org.meogo.domain.post.present.dto.request.PostRequest
import org.meogo.domain.post.service.CreatePostService
import org.meogo.domain.post.service.QueryAllPostService
import org.meogo.domain.post.service.QuerySchoolPostService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
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
    private val querySchoolPostService: QuerySchoolPostService
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

    @GetMapping("/query/all")
    fun queryAll() = queryAllPostService.execute()

    @GetMapping("/query")
    fun querySchool(@RequestParam(name = "school_id") schoolId: Int) = querySchoolPostService.execute(schoolId)
}
