package org.meogo.domain.review.domain

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Review(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    val date: LocalDateTime,

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    val userId: UUID,

    @Column(name = "school_id", nullable = false)
    val schoolId: Int,

    @Column(nullable = false)
    var star: Float,

    @Column(nullable = false)
    var content: String,

    @Column(name = "key_word")
    val keyWord: String?,

    var picture: String?

) {
    fun updateReview(
        content: String,
        star: Float,
        picture: String?
    ): Review {
        this.content = content
        this.star = star
        this.picture = picture
        return this
    }
}
