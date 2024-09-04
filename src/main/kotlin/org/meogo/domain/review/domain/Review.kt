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

    val date: LocalDateTime,

    @Column(columnDefinition = "BINARY(16)")
    val userId: UUID,

    val userName: String,

    val schoolId: Int,

    val star: Long,

    val content: String,

    val picture: String?

)
