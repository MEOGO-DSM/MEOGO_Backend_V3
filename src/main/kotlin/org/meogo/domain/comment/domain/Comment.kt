package org.meogo.domain.comment.domain

import org.meogo.domain.post.domain.Post
import org.meogo.domain.user.domain.User
import java.time.LocalDateTime
import javax.persistence.CascadeType.REMOVE
import javax.persistence.Column
import javax.persistence.Entity
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

    @ManyToOne(cascade = [REMOVE], fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post,

    @ManyToOne(cascade = [REMOVE], fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
)
