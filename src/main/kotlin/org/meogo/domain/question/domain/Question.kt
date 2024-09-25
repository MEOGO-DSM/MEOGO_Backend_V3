package org.meogo.domain.question.domain

import org.meogo.domain.question.enum.QuestionType
import org.meogo.domain.user.domain.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Question(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    var content: String,

    @Column(nullable = false)
    val date: LocalDateTime,

    @Column(name = "school_id", nullable = false)
    val schoolId: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    var questionType: QuestionType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) {
    fun modify(content: String, questionType: QuestionType): Question {
        this.content = content
        this.questionType = questionType
        return this
    }

    fun format(date: LocalDateTime) = date.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"))!!
}
