package org.meogo.domain.post.domain

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Post(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val content: String,

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    val userId: UUID,

    @Column(nullable = false)
    var good: Int = 0,

    @Column(nullable = false)
    val date: LocalDateTime,

    @Column(name = "school_id")
    val schoolId: Int?,

    @Column(name = "key_word")
    val keyWord: String?
) {
    fun addGood() {
        this.good += 1
    }
}
