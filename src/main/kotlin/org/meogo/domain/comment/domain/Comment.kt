package org.meogo.domain.comment.domain

import org.meogo.domain.comment.enum.CommentType
import org.meogo.domain.post.domain.Post
import org.meogo.domain.question.domain.Question
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
class Comment(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    var content: String,

    @Column(nullable = false)
    val date: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name = "comment_type", nullable = false)
    val commentType: CommentType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = true)
    val comment: Comment? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = true)
    val post: Post? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = true)
    val question: Question? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) {
    fun format(date: LocalDateTime) = date.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"))!!
}
