package org.meogo.domain.review.presentation.dto.response

import org.meogo.domain.review.enum.KeyWordCategory

data class QueryKeywordsResponse(
    val keyword: String,
    val category: KeyWordCategory
)
