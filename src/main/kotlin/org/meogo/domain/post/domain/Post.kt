package org.meogo.domain.post.domain

import org.meogo.domain.comment.domain.Comment
import org.meogo.domain.user.domain.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Post(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    var good: Int = 0,

    @Column(nullable = false)
    val date: LocalDateTime,

    @Column(name = "school_id")
    var schoolId: Int?,

    @Column(name = "key_word")
    var keyWord: String?,

    var image: String?,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: List<Comment> = emptyList()
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

    fun deleteGood() {
        this.good -= 1
    }

    fun format(date: LocalDateTime) = date.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"))!!
}
