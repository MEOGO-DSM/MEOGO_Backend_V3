package org.meogo.domain.post.present.dto.request

import javax.validation.constraints.Size

data class PostRequest(
    @field: Size(min = 1, max = 20)
    val title: String,
    @field: Size(min = 1, max = 300)
    val content: String,
    val isOk: Boolean,
    val image: List<String>?,
    val keyWord: List<String>?
)