package org.meogo.domain.good.presentation

import org.meogo.domain.good.service.GoodService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/good")
class GoodController(
    private val goodService: GoodService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun addGood(@RequestParam(name = "post_id")postId: Long) =
        goodService.addGood(postId)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    fun deleteGood(@RequestParam(name = "post_id")postId: Long) =
        goodService.deleteGood(postId)

    @GetMapping("/query/my")
    fun myGoods() =
        goodService.queryMyGoods()
}
