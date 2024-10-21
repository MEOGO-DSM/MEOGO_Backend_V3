package org.meogo.domain.good.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.meogo.domain.good.service.GoodService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Good API")
@RestController
@RequestMapping("/good")
class GoodController(
    private val goodService: GoodService
) {
    @Operation(summary = "좋아요")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun addGood(@RequestParam(name = "post_id")postId: Long) =
        goodService.addGood(postId)

    @Operation(summary = "좋아요 취소")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    fun deleteGood(@RequestParam(name = "post_id")postId: Long) =
        goodService.deleteGood(postId)

    @Operation(summary = "내 좋아요 목록 조회")
    @GetMapping("/query/my")
    fun myGoods() =
        goodService.queryMyGoods()
}
