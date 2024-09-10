package org.meogo.domain.post.present

import lombok.RequiredArgsConstructor
import org.meogo.domain.post.present.dto.request.PostRequest
import org.meogo.domain.post.service.CreatePostService
import org.meogo.domain.post.service.QueryAllPostService
import org.meogo.domain.post.service.QuerySchoolPostService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
class PostController(
    private val createPostService: CreatePostService,
    private val queryAllPostService: QueryAllPostService,
    private val querySchoolPostService: QuerySchoolPostService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @Valid @RequestBody
        request: PostRequest
    ) =
        createPostService.execute(request)

    @GetMapping("/query/all")
    fun queryAll() = queryAllPostService.execute()

    @GetMapping("/query/school")
    fun querySchool(@RequestParam(name = "school_id") schoolId: Int) = querySchoolPostService.execute(schoolId)
}
