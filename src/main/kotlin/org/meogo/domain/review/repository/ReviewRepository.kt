package org.meogo.domain.review.repository

import org.meogo.domain.review.domain.Review
import org.springframework.data.repository.Repository

interface ReviewRepository : Repository<Review, Long> {
    fun save(review: Review): Review

    fun findAllBySchoolId(id: Int): List<Review>

    fun findById(id: Long): Review?
}
