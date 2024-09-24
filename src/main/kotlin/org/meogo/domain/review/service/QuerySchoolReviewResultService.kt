package org.meogo.domain.review.service

import org.meogo.domain.review.domain.ReviewRepository
import org.meogo.domain.review.enum.KeyWord
import org.meogo.domain.review.enum.KeyWordCategory
import org.meogo.domain.review.presentation.dto.response.ReviewResultResponse
import org.meogo.domain.review.presentation.dto.response.Tag
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuerySchoolReviewResultService(
    private val reviewRepository: ReviewRepository
) {

    @Transactional(readOnly = true)
    fun execute(schoolId: Int): ReviewResultResponse {
        val reviews = reviewRepository.findAllBySchoolId(schoolId) ?: emptyList()
        val categoryCounts = mutableMapOf<KeyWordCategory, Int>()
        val keywordMap = mutableMapOf<KeyWordCategory, MutableMap<String, Int>>()

        val aveStar = if (reviews.isNotEmpty()) {
            reviews.map { it.star }.average().toFloat().let { "%.1f".format(it).toFloat() }
        } else {
            0f
        }

        reviews.forEach { review ->
            review.keyWord.split(", ").forEach { keyword ->
                KeyWord.entries.find { it.keyword == keyword }?.category?.let { category ->
                    categoryCounts[category] = (categoryCounts[category] ?: 0) + 1

                    val currentCount = keywordMap.getOrPut(category) { mutableMapOf() }[keyword] ?: 0
                    keywordMap[category]!![keyword] = currentCount + 1
                }
            }
        }

        val totalKeys = reviews.sumOf { it.keyWord.split(", ").size }

        val tags = categoryCounts.map { (category, count) ->
            val percentage = if (totalKeys > 0) {
                (count.toFloat() / totalKeys * 100).toInt()
            } else {
                0
            }

            val repKeyword = keywordMap[category]?.maxByOrNull { it.value }?.key ?: "없음"

            Tag(
                tagName = repKeyword,
                percentage = percentage
            )
        }

        return ReviewResultResponse(
            star = aveStar,
            tag1 = tags.getOrNull(0) ?: Tag("전체 만족", 0),
            tag2 = tags.getOrNull(1) ?: Tag("교내 시설", 0),
            tag3 = tags.getOrNull(2) ?: Tag("교내 활동", 0)
        )
    }
}

// @Service
// class QuerySchoolReviewResultService(
//    private val reviewRepository: ReviewRepository
// ) {
//
//    @Transactional(readOnly = true)
//    fun execute(schoolId: Int): ReviewResultResponse {
//        val reviews = reviewRepository.findAllBySchoolId(schoolId) ?: emptyList()
//
//        // 평균 별점
//        val aveStar = if (reviews.isNotEmpty()) {
//            "%.1f".format(reviews.map { it.star }.average()).toFloat()
//        } else {
//            0f
//        }
//
//        val categoryCounts = mutableMapOf<KeyWordCategory, Int>()
//        val keywordMap = mutableMapOf<KeyWordCategory, MutableMap<String, Int>>()
//
//        reviews.forEach { review ->
//            val keywords = review.keyWord.split(", ")
//            keywords.forEach { keyword ->
//                val category = KeyWord.entries.find { it.keyword == keyword }?.category
//                category?.let {
//                    categoryCounts[it] = categoryCounts.getOrDefault(it, 0) + 1
//                    keywordMap.getOrPut(it) { mutableMapOf() }[keyword] =
//                        keywordMap[it]?.getOrDefault(keyword, 0)?.plus(1) ?: 1
//                }
//            }
//        }
//
//        // 키워드별 비율
//        val totalKeywords = reviews.flatMap { it.keyWord.split(", ") }.size
//
//        val tags = categoryCounts.map { (category, count) ->
//            val percentage = if (totalKeywords > 0) {
//                (count.toFloat() / totalKeywords * 100).toInt()
//            } else {
//                0
//            }
//
//            val repKeyword = keywordMap[category]?.maxByOrNull { it.value }?.key ?: "없음"
//
//            Tag(
//                tagName = repKeyword,
//                percentage = percentage
//            )
//        }
//
//        // 최종 응답 생성
//        return ReviewResultResponse(
//            star = aveStar,
//            tag1 = tags.getOrNull(0) ?: Tag("전체 만족", 0),
//            tag2 = tags.getOrNull(1) ?: Tag("교내 시설", 0),
//            tag3 = tags.getOrNull(2) ?: Tag("교내 활동", 0)
//        )
//    }
// }
