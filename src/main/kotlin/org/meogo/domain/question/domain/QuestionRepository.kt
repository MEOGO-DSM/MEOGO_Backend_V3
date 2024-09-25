package org.meogo.domain.question.domain

import org.springframework.data.repository.Repository

interface QuestionRepository : Repository<Question, Long> {
    fun save(question: Question)
    fun findById(id: Long): Question?
}
