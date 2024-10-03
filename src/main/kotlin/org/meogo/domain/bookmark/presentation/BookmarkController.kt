package org.meogo.domain.bookmark.presentation

import org.meogo.domain.bookmark.service.BookmarkService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bookmark")
class BookmarkController(
    private val bookmarkService: BookmarkService
) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    fun create(@RequestParam(name = "school_id")schoolId: Int) =
        bookmarkService.execute(schoolId)

    @GetMapping("/query/my")
    fun myBookmarks() =
        bookmarkService.queryBookmarkedSchool()

    @GetMapping("/query")
    fun isBookmarked(@RequestParam(name = "school_id")schoolId: Int) =
        bookmarkService.queryIsBookmarked(schoolId)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    fun delete(@RequestParam(name = "school_id")schoolId: Int) =
        bookmarkService.deleteBookmark(schoolId)
}
