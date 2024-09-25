package org.meogo.domain.question.domain

import org.springframework.data.repository.Repository

interface QuestionRepository : Repository<Question, Long> {
    fun findById(id: Long): Question?
}
