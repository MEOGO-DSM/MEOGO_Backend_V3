package org.meogo.domain.question.domain

import org.meogo.domain.question.enum.QuestionType
import org.springframework.data.repository.Repository

interface QuestionRepository : Repository<Question, Long> {
    fun save(question: Question)

    fun findById(id: Long): Question

    fun findBySchoolId(schoolId: Int): List<Question>

    fun findBySchoolIdAndQuestionType(schoolId: Int, questionType: QuestionType): List<Question>

    fun deleteById(id: Long)
}
