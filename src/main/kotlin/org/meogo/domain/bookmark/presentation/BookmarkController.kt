package org.meogo.domain.bookmark.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.meogo.domain.bookmark.service.BookmarkService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Bookmark API")
@RestController
@RequestMapping("/bookmark")
class BookmarkController(
    private val bookmarkService: BookmarkService
) {

    @Operation(summary = "북마크 생성")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    fun create(@RequestParam(name = "school_id")schoolId: Int) =
        bookmarkService.execute(schoolId)

    @Operation(summary = "내 북마크 조회")
    @GetMapping("/query/my")
    fun myBookmarks() =
        bookmarkService.queryBookmarkedSchool()

    @Operation(summary = "북마크 여부 조회")
    @GetMapping("/query")
    fun isBookmarked(@RequestParam(name = "school_id")schoolId: Int) =
        bookmarkService.queryIsBookmarked(schoolId)

    @Operation(summary = "북마크 취소")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    fun delete(@RequestParam(name = "school_id")schoolId: Int) =
        bookmarkService.deleteBookmark(schoolId)
}
