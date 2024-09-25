package org.meogo.domain.review.service

import org.meogo.domain.review.enum.KeyWord
import org.meogo.domain.review.presentation.dto.response.QueryKeywordsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryKeywordsService {
    @Transactional(readOnly = true)
    fun execute(): List<QueryKeywordsResponse> {
        return KeyWord.entries.map { QueryKeywordsResponse(it.keyword, it.category) }
    }
}
