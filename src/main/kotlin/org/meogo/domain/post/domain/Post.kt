package org.meogo.domain.post.domain

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
    var title: String,

    @Column(nullable = false)
    var content: String,

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    val userId: UUID,

    @Column(nullable = false)
    var good: Int = 0,

    @Column(nullable = false)
    val date: LocalDateTime,

    @Column(name = "school_id")
    var schoolId: Int?,

    @Column(name = "key_word")
    var keyWord: String?,

    var image: String?
) {
    fun update(title: String, content: String, schoolId: Int? = null, keyWord: String? = null, image: String?): Post {
        this.title = title
        this.content = content
        this.schoolId = schoolId
        this.keyWord = keyWord
        this.image = image
        return this
    }

    fun addGood() {
        this.good += 1
    }

    fun format(date: LocalDateTime) = date.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"))!!
}
